package school.sptech.sistema_estoque.dto;

import java.util.List;

public record TagsRequest(
        Integer totalItems,
        List<Tag> tags
) {}
