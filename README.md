# wiseclinic-backend
Projeto Final de Programação Orientada a Objetos Back-end

### Diagrama de classe UML

```plantuml
@startuml
    scale 650 width
    skinparam linetype ortho
    
    class Paciente{
        - cpf: String
        - nome: String
        - endereco: String
        - email: String
        - telefone: String
        - genero: String
        - dataNasc: LocalDate
        - {field} convenio: Convenio
        - listaConsulta: ArrayList
        + agendarConsulta(*)
        + cancelarConsulta(*)
        + gets/sets()
    }

    interface Consulta{
        -paciente: Paciente
        -{field}horario: LocalDate
        -checkIn: boolean
        -id: int
        +trocarProfissional(*)
        +trocarHorario(*)
        +checkInPaciente(*)
        +gets/sets()
    }

    class ConsultaMedica{
        -medico: Medico
        +trocarProfissional(*)
        +trocarHorario(*)
        +checkInPaciente(*)
        +gets/sets()
    }

    class ConsultaOdontologica{
        -dentista: Dentista
        +trocarProfissional(*)
        +trocarHorario(*)
        +checkInPaciente(*)
        +gets/sets()
    }

    abstract class Profissional{
        -agenda: Agenda
        -nome: String
        -cpf: String
        -email: String
        -listaConsulta: ArrayList
        +atualizarAgenda(*)
        +agendarConsulta(*)
        +cancelarConsulta(*)
        +gets/sets()
    }

    class Dentista{
        -cro: String
        -especialidades: ArrayList
        +adicionarEspecialidade(*)
        +gets/sets()
    }

    class Medico{
        -crm: String
        -especialidades: ArrayList
        +adicionarEspecialidade(*)
        +gets/sets()
    }

    class Agenda{
        -{field} dias(horario: boolean)
        +gets/sets()
    }

    enum Convenio{
    }

    enum Especialidade{
    }

    Profissional <|-- Medico
    Profissional <|-- Dentista

    Consulta <|.. ConsultaOdontologica
    Consulta <|.. ConsultaMedica

    Paciente --> "0..1 " Convenio
    Paciente "0..* " *-->  ConsultaMedica
    Paciente *---> "0..* " ConsultaOdontologica

    Medico o---> "0..*  " ConsultaMedica
    Dentista o--> " 0..*" ConsultaOdontologica

    Medico --> "1..*" Especialidade
    Dentista --> "1..*   " Especialidade

    
    Profissional --> "1 " Agenda

}
@enduml
```

