package hei.school.soratra.service.soratra;

import hei.school.soratra.endpoint.rest.controller.soratra.dto.Soratra;
import hei.school.soratra.file.BucketComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SoratraService {
    private final BucketComponent bucket;

    public Optional<String> saveSoratra(String id, byte[] payload) throws IOException {
        String originalSoratra = new String(payload, StandardCharsets.UTF_8);
        String modifiedSoratra = originalSoratra.toUpperCase();

        File originalFile = File.createTempFile("og_" + id, ".txt");
        File modifiedFile = File.createTempFile("md_" + id, ".png");

        Files.writeString(
                Path.of(originalFile.getAbsolutePath()),
                originalSoratra,
                StandardOpenOption.WRITE
        );

        Files.writeString(
                Path.of(modifiedFile.getAbsolutePath()),
                modifiedSoratra,
                StandardOpenOption.WRITE
        );

        bucket.upload(originalFile, "og_" + id);
        bucket.upload(modifiedFile, "md_" + id);

        return Optional.of(id);
    }

    public Optional<Soratra> getSoratra(String id) {
        return Optional.of(
                new Soratra(
                        bucket.presign("og_" + id, Duration.ofMinutes(20)),
                        bucket.presign("md_" + id, Duration.ofMinutes(20))
                )
        );
    }
}
