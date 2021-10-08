package se.hedvig.task.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import se.hedvig.task.utils.LocalDateDeserializer;
import se.hedvig.task.utils.LocalDateSerializer;

import java.time.LocalDate;

@Getter
@Setter
public class Event {

    private EventType type;
    private Long contactId;
    private Integer value;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;

}
