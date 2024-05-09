package example;

public class DeviceController {

    public void sendShutDown() {
        DeviceHandle handle = getHandle(DEV1);

        if (handle != DeviceHandle.INVALID) {
            retrieveDeviceRecord(handle);

            if (record.getStatus() != DEVICE_SUSPENDED) {
                pauseDevice(handle);
                clearDeviceWorkQueue(handle);
                closeDevice(handle);
            } else {
                logger.log("Device suspended. Unable to shut down");
            }
        } else {
            logger.log("Invalid handle for: " + DEV1.toString());
        }
    }
}
