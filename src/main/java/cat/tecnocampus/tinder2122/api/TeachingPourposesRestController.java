package cat.tecnocampus.tinder2122.api;

import cat.tecnocampus.tinder2122.application.TinderController;
import cat.tecnocampus.tinder2122.application.dto.ProfileDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;

@RestController
@RequestMapping("/teaching")
@Validated
public class TeachingPourposesRestController {
    TinderController tinderController;

    public TeachingPourposesRestController(TinderController tinderController) {
        this.tinderController = tinderController;
    }

    @GetMapping("/int/{i}")
    public int getInt(@PathVariable @Max(50) int i) {
        return i;
    }

    @PostMapping("/profilesString")
    public String addProfile(@RequestBody String profile) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ProfileDTO profileDTO = objectMapper.readValue(profile, ProfileDTO.class);

        ProfileDTO response = tinderController.addProfile(profileDTO);;

        return objectMapper.writeValueAsString(response);
    }

}
