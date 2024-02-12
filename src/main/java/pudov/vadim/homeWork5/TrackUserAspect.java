package pudov.vadim.homeWork5;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class TrackUserAspect {
    @After("@annotation(pudov.vadim.homeWork5.TrackUserAction)")
    public void trackActions(JoinPoint joinPoint) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String methodName = joinPoint.getSignature().getName();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Attention! Method " + methodName + " was implemented by " + auth.getName() + ".");

        BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true));
        writer.append("\n").append(formatter.format(LocalDateTime.now()))
                .append("  Method \"")
                .append(methodName)
                .append("\" was implemented by \"")
                .append(auth.getName())
                .append("\".");
        writer.close();
    }
}
