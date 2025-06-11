package demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@Getter
@Builder
@AllArgsConstructor
@ToString
public class Engine {
    private String type;
    private String model;
}

