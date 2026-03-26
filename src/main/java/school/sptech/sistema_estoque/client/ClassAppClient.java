package school.sptech.sistema_estoque.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import school.sptech.sistema_estoque.config.FeignConfig;
import school.sptech.sistema_estoque.dto.TagsResponse;

@FeignClient(
        name= "classAppClient",
        url= "https://api.classapp.com.br/v1",
        configuration = FeignConfig.class
)
public interface ClassAppClient {
    @GetMapping("/tags")
    public TagsResponse getTags();

    @GetMapping("/groups")
    public String getGroups();
}
