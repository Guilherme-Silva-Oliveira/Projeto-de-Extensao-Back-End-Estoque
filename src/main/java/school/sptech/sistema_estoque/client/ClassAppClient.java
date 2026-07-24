package school.sptech.sistema_estoque.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import school.sptech.sistema_estoque.config.FeignConfig;
import school.sptech.sistema_estoque.dto.classapp.LabelsRequest;
import school.sptech.sistema_estoque.dto.classapp.MensagemRequest;
import school.sptech.sistema_estoque.dto.classapp.TagsRequest;

@FeignClient(
        name= "classAppClient",
        url= "https://api.classapp.com.br/v1",
        configuration = FeignConfig.class
)
public interface ClassAppClient {
    @GetMapping("/tags")
    TagsRequest getTags();

    @GetMapping("/groups")
    String getGroups();

    @GetMapping("/student")
    String getStudents();

    @GetMapping("/staff")
    String getStaffs();

    @GetMapping("/labels")
    LabelsRequest getLabels();

    @PostMapping("/message")
    String enviarMensagem(@RequestBody MensagemRequest request);
}
