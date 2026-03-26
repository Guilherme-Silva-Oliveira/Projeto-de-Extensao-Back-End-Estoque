package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.client.ClassAppClient;
import school.sptech.sistema_estoque.dto.TagsResponse;

@Service
public class SistemaService {
    private final ClassAppClient client;
    public SistemaService(ClassAppClient client) {
        this.client = client;
    }

    public TagsResponse getTags(){
        return client.getTags();
    }
}
