
package killbit.taskrabbit.retrofit.bookingStep1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DropdownData {

    @SerializedName("subcat_list")
    @Expose
    private SubcatList subcatList;
    @SerializedName("vehicle_list")
    @Expose
    private VehicleList vehicleList;
    @SerializedName("timing_list")
    @Expose
    private TimingList timingList;

    public SubcatList getSubcatList() {
        return subcatList;
    }

    public void setSubcatList(SubcatList subcatList) {
        this.subcatList = subcatList;
    }

    public VehicleList getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(VehicleList vehicleList) {
        this.vehicleList = vehicleList;
    }

    public TimingList getTimingList() {
        return timingList;
    }

    public void setTimingList(TimingList timingList) {
        this.timingList = timingList;
    }

}
