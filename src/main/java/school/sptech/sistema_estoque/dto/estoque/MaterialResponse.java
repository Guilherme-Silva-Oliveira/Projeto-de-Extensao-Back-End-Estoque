package school.sptech.sistema_estoque.dto.estoque;

import school.sptech.sistema_estoque.model.estoque.Categoria;

public record MaterialResponse(
        Integer id,
        Categoria categoria
) {}
