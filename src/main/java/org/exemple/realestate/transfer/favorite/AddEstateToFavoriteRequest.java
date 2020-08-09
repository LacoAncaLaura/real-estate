package org.exemple.realestate.transfer.favorite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddEstateToFavoriteRequest {
    @NotNull
    private List<Long> estateIds;
}
