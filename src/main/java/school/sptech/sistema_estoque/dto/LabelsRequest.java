package school.sptech.sistema_estoque.dto;

import java.util.List;

public record LabelsRequest(
        Integer totalItems,
        List<Label> labels
){}
