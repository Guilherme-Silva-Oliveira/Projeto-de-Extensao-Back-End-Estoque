package school.sptech.sistema_estoque.dto;

import java.util.List;

public record TagsResponse(
        Integer totalItems,
        List<Tag> tags
) {}
