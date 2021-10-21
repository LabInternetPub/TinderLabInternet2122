package cat.tecnocampus.tinder2122.application.aop;

import cat.tecnocampus.tinder2122.application.dto.ProfileDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class LoggerAdvice {
	
    private static final Logger logger = LoggerFactory.getLogger(LoggerAdvice.class);


    //A pointcut that matches one single method
    @Pointcut("execution(* cat.tecnocampus.tinder2122.application.TinderController.getProfilesLazy(..))")
    public void pointcutListProfilesLazy() {}

    //Before advice of a pointcut
    @Before("pointcutListProfilesLazy()")
    public void beforeListUsers() {
        logger.info("Going to list all profiles in a lazy fashion");
    }

    //After advice of a pointcut
    @After("pointcutListProfilesLazy()")
    public void afterListUsers() {
        logger.info("Already listed all profiles in a lazy fashion");
    }


    //A pointcut that matches all methods having the word "Likes" in any position of methods' name
    @Pointcut("execution(* cat.tecnocampus.tinder2122.application.TinderController.*Likes*(..))")
    public void pointcutLikes() {}

    //Before advice of a pointcut
    @Before("pointcutLikes()")
    public void beforeDealingNotes() {
        logger.info("Going to deal with likes");
    }

    //A pointcut that matches all methods returning a List<ProfileDTO> and its name ending with "Eager"
    @Pointcut("execution(public java.util.List<cat.tecnocampus.tinder2122.application.dto.ProfileDTO> cat.tecnocampus.tinder2122.application.TinderController.*Eager(..))")
    public void listEagerProfiles() {}

    //Around advice. Note that this method must return what the proxied method is supposed to return
    @Around("listEagerProfiles()")
    public List<ProfileDTO> dealRequestParam(ProceedingJoinPoint jp) {

        try {
            logger.info("Before showing profiles in eager fashion");
            List<ProfileDTO> res = (List<ProfileDTO>) jp.proceed();
            logger.info("Going to return all profiles in eager fashion");
            return res;
        } catch (Throwable throwable) {
            logger.info("Something went wrong when attempting to list all profiles in eager fashion");
            throwable.printStackTrace();
            return new ArrayList<ProfileDTO>();
        }
    }

    //Getting the parameters of the proxied method
    @Pointcut("execution(* cat.tecnocampus.tinder2122.application.TinderController.getCandidates(..)) && args(id)")
    public void showCandidatesPointcut(String id) {}

    @Before("showCandidatesPointcut(id)")
    public void showUserAdvice(String id) {
        logger.info("Going to show candidates for: " + id);
    }

}
