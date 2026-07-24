package school.sptech.sistema_estoque.dto.classapp;

import java.util.List;

public record MessageData(

        String subject,

        String content,

        String type,

        List<String> tags,

        Boolean noReply,

        String groupedMessageId,

        String label,

        Integer labelId,

        Recipients recipients

) {}