# wiseclinic-backend
Projeto Final de Programação Orientada a Objetos Back-end

O projeto consiste em um sistema de consultas de uma clínica médica/odontológica, em que é possível:

- Agendar Consultas;
- Cancelar Consultas;
- Listar Consultas;
- Realizar check-in do Paciente na Consulta.

Para isso, foi criada uma API seguindo o padrão REST em Java utilizando o framework Spring Boot, que permite a criação de endpoints HTTP. A interface gráfica foi feita utilizando tecnologias como TypeScript e React para a composição das telas da aplicação.

Abaixo, segue a estrutura de classes utilizada para o sistema:

### Diagrama de classe UML

```plantuml
@startuml
    scale 700 width
    skinparam linetype ortho
    !theme sandstone

    class Paciente{
        - cpf: String
        - nome: String
        - endereco: String
        - email: String
        - telefone: String
        - genero: String
        - dataNasc: LocalDate
        - convenio: Convenio
        - listaConsulta: List<Consulta>
        + agendarConsulta(*)
        + cancelarConsulta(*)
        + gets/sets()
    }

    class Consulta{
        -paciente: Paciente
        -horario: LocalDateTime
        -checkIn: boolean
        -id: UUID
        -especialidade: Especialidade
        -area: Area
        -profissional: Profissional
        +checkInPaciente(*)
        +gets/sets()
    }

    abstract class Profissional{
        -agenda: Agenda
        -nome: String
        -cpf: String
        -email: String
        -listaConsulta: List<Consulta>
        +agendarConsulta(*)
        +cancelarConsulta(*)
        +gets/sets()
    }

    class Dentista{
        -cro: String
        -especialidades: List<EspecialidadeDentista>
        +adicionarEspecialidade(*)
        +agendarConsulta(*)
        +cancelarConsulta(*)
        +gets/sets()
    }

    class Medico{
        -crm: String
        -especialidades: List<EspecialidadeMedica>
        +adicionarEspecialidade(*)
        +agendarConsulta(*)
        +cancelarConsulta(*)
        +gets/sets()
    }

    class Agenda{
        -horariosOcupados: List<LocalDateTime>
        +liberarHorario(*)
        +ocuparHorario(*)
        +isHorarioOcupado(*)
        +filtrarHorario(*)
        +gets()
        
    }

    enum Area {
        -MEDICINA
        -ODONTOLOGIA
    }

    interface GerenciadorConsulta{
        + agendarConsulta(*)
        + cancelarConsulta(*)
    }

    enum Convenio{
        -SULAMERICA
        -AMIL
        -BRADESCO
        -UNIMED
        __
        nome: String
        chave: int
    }

    interface Especialidade {
        +getCod(): int
        +getEspecialidadePorCod(*): Especialidade
    }

    enum EspecialidadeMedica{
        -ALERGOLOGIA
        -CARDIOLOGIA
        -DERMATOLOGIA
        -NEUROLOGIA
        -OFTALMOLOGIA
        -PEDIATRIA
        __
        nome: String
        codigo: int
    }

    enum EspecialidadeDentista{
        -ORTODONTIA
        -PERIODONTIA
        -ODONTOPEDIATRIA
        __
        nome: String
        codigo: int
    }




    Profissional <|-- Medico
    Profissional <|-- Dentista

    Especialidade <|.. EspecialidadeMedica
    Especialidade <|.. EspecialidadeDentista

    Profissional ..|> GerenciadorConsulta
    Paciente ..|> GerenciadorConsulta

    Paciente --> "0..1" Convenio
    Paciente *--> "0..*"  Consulta
    Paciente *--> "0..* " Consulta

    Medico o--> "0..* " Consulta
    Dentista o--> "0..*" Consulta

    Medico --> "1..*" EspecialidadeMedica
    Dentista --> "1..*" EspecialidadeDentista

    Consulta --> Especialidade

    Consulta o--> "1" Area
    Profissional o--> "1" Agenda

@enduml
```
