package cat.tecnocampus.tinder2122.api;

import cat.tecnocampus.tinder2122.application.TinderController;
import cat.tecnocampus.tinder2122.application.dto.ProfileDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.simpleflatmapper.jdbc.spring.RowMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

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

    /*******************************************
     * DANGER ZONE. COMPLETELY FORBIDDEN THAT THE FRONT-END LAYER TALKS DIRECTLY TO THE DATABASE
     */

    // Forbidden to inject a dependency directly to the attribute
    @Autowired
    private JdbcTemplate jdbcTemplate;

    RowMapperImpl<ProfileDTO> profileRowMapper =
            JdbcTemplateMapperFactory
                    .newInstance()
                    .addKeys("id")
                    .newRowMapper(ProfileDTO.class);

    @GetMapping("/profilesProblems/{id}")
    public List<ProfileDTO> profilesProblems(@PathVariable String id) {
        final String queryProfilesLazy = "select id, email, nickname, gender, attraction, passion from tinder_user where id = " + id;
        return jdbcTemplate.query(queryProfilesLazy, profileRowMapper);
    }


}
