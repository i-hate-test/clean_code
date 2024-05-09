package example;

public class LocalPort {

    private ACMEPort innerPort;

    public LocalPort(int portNumber) {
        this.innerPort = new ACMEPort(portNumber);
    }

    public void open() {
        try {
            port.open();
        } catch (DeviceResponseException e) {
            reportPortError(e);
            logger.log("Device response exception", e);
        } catch (ATM1212UnlockedException e) {
            reportPortError(e);
            logger.log("Device response exception", e);
        } catch (GMXError e) {
            reportPortError(e);
            logger.log("Device response exception", e);
        }
    }
}
