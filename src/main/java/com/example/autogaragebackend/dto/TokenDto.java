package com.example.autogaragebackend.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.token.DefaultToken;

import java.time.LocalDateTime;

@Data
@Builder
public class TokenDto {

    private String token;

    private LocalDateTime issuedAt;

    private LocalDateTime expiredAt;

    private String userEmail;

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof DefaultToken) {
            TokenDto rhs = (TokenDto) obj;
            return this.token.equals(rhs.token) && this.issuedAt == rhs.issuedAt
                    && this.userEmail.equals(rhs.userEmail);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int code = 979;
        code = code * this.token.hashCode();
        code = code * Long.valueOf(this.issuedAt.getNano()).hashCode();
        code = code * this.userEmail.hashCode();
        return code;
    }
}
