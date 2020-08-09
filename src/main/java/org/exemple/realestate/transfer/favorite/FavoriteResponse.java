package org.exemple.realestate.transfer.favorite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FavoriteResponse {
    private long id;
    private List<EstateInFavoriteResponse> estates;
}
