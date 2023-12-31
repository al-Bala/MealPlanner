package pl.mealplanner.amazons3;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ImageController {

    @Autowired
    private AmazonS3 s3client;

    private final String bucketName = "mealplannerup";

    @GetMapping("/image/{imageName}")
    public String getImageUrl(@PathVariable String imageName, Model model) {
        String imageUrl = "https://" + bucketName + ".s3.amazonaws.com/" + imageName;
        model.addAttribute("imageUrl", imageUrl);
        return "nazwa_widoku";
    }
}
