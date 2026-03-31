package school.sptech.sistema_estoque.dto.classapp;

import java.util.List;

public record LabelsRequest(
        Integer totalItems,
        List<Label> labels
){}
