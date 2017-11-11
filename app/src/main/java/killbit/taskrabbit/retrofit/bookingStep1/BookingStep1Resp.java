
package killbit.taskrabbit.retrofit.bookingStep1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingStep1Resp {

    @SerializedName("dropdown_data")
    @Expose
    private DropdownData dropdownData;
    @SerializedName("status")
    @Expose
    private Integer status;

    public DropdownData getDropdownData() {
        return dropdownData;
    }

    public void setDropdownData(DropdownData dropdownData) {
        this.dropdownData = dropdownData;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
