package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.professor.ProfessorRequest;
import school.sptech.sistema_estoque.dto.estoque.professor.ProfessorResponse;
import school.sptech.sistema_estoque.model.estoque.Professor;

public class ProfessorMapper {
    public static Professor toEntity(ProfessorRequest professorRequest){
        Professor entity = new Professor();
        entity.setEmail(professorRequest.email());
        entity.setNome(professorRequest.nome());
        entity.setTelefone(professorRequest.telefone());
        return entity;
    }

    public static ProfessorResponse toResponse(Professor entity){
        return new ProfessorResponse(entity.getId(), entity.getNome(), entity.getEmail(), entity.getTelefone());
    }
}
