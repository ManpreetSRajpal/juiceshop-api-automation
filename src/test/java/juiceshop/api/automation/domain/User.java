package juiceshop.api.automation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class User {
    @NonNull
    private String email;
    @NonNull
    private String password;
    private String passwordRepeat;
}
