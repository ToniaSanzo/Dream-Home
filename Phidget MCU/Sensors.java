import com.phidget22.*;      //Phidget .jar
import java.util.Scanner;

/****************************************************************************
* Program Name   : Sensors class
* Group          : Smart House
* Date           : 11/27/18
* Course/Section : CSC 490
* Program Description: This class instantiates a group of sensor channels.
*
* Constructors:
* void Sensors - Sets up the channels for the various phidgets.
*
* Getters+:
* +RFIDSensor     getRFIDCH            - Returns the RFID channel.
* +DistanceSensor getDistanceCH        - Returns the Distance channel.
* +HumiditySensor getHumidityCH        - Returns the Humidity channel.
* +DigitalOutput  getOutputCH (String) - Returns the desired InterfaceKit digital output channel.
* 
* Methods+-:
* +void setUpChannels      - Calls methods to open channels and set up the phidgets.
* +void getData (int)      - Gets data for the specified period of time.
* +void closeChannels      - Closes all channels.
* -void openChannels       - Opens all channels.
* -void createAttachEvents - Creates attach events and adds them to the phidgets.
* -void createDetachEvents - Creates detach events and adds them to the phidgets.
* -void createErrorEvents  - Creates error events and adds them to the phidgets.
* -void addFunctionality   - Creates listeners for unique events with each sensor.
****************************************************************************/
public class Sensors {
        
        //class constants
        
        //class variables
        public  boolean rfidPositiveFlag  = false;    //True when a positive card ID is read
        public  boolean rfidNegativeFlag  = false;    //True when a negative card ID is read
        public  boolean humidityFlag      = false;    //True when sensor reading goes above maxHumidity
        public  boolean distanceFlag      = false;    //True when sonar detects motion in front of sensor
        private boolean distanceSet       = false;    //True when base distance is set
        private boolean livingRoomLight   = false;    //Alternating lights when someone enters the living room
        private boolean bathroomLight     = false;    //... and bathroom
        private double  maxHumidity       = 59.5;     //Humidity threshold used for turning on the fan
        private int     baseDistance;                 //When first run, initializes the distance sensors base distance
        
        private RFID           rfidCH;         //RFID channel
        private DistanceSensor distanceCH;     //Distance channel
        private HumiditySensor humidityCH;     //Humidity channel
        private DigitalOutput  outputCH0;      //InterfaceKit channel, used for the RFID red light
        private DigitalOutput  outputCH1;      //InterfaceKit channel, used for the RFID green light
        private DigitalOutput  outputCH2;      //InterfaceKit channel, used for the living room light
        private DigitalOutput  outputCH3;      //InterfaceKit channel, used for the bathroom light
        private DigitalOutput  outputCH4;      //InterfaceKit channel, used for the dcmotor fan
        
        private Scanner s = new Scanner(System.in);    //Reads inputs
        
    /****************************************************************************
    * Method Name    : Sensors constructor
    * Authors        : Cameron, Tonia, Shelly
    * Date           : 11/27/18
    * Course/Section : CSC 490
    * Method Description: Sets up the channels for the various phidgets.
    *
    * BEGIN Sensors
    *    Set up RFID channel
    *    Set up distance channel
    *    Set up humidity channel
    *    Set up InterfaceKit channels for lights and fan
    * END   Sensors
    ****************************************************************************/
        Sensors () throws Exception {
                
                /********** start Sensors constructor **********/
                //RFID channel
                rfidCH = new RFID();
                rfidCH.setDeviceSerialNumber(518217);
                rfidCH.setIsHubPortDevice(false);
                rfidCH.setChannel(0);
                
                //Distance channel
                distanceCH = new DistanceSensor();
                distanceCH.setDeviceSerialNumber(496561);
                distanceCH.setHubPort(0);
                distanceCH.setChannel(0);
                
                //Humidity channel
                humidityCH = new HumiditySensor();
                humidityCH.setDeviceSerialNumber(496561);
                humidityCH.setHubPort(1);
                humidityCH.setChannel(0);
                
                //InterfaceKit channels
                //...for the RFID red light
                outputCH0 = new DigitalOutput();
                outputCH0.setDeviceSerialNumber(511546);
                outputCH0.setIsHubPortDevice(false);
                outputCH0.setChannel(0);

                //...for the RFID green light
                outputCH1 = new DigitalOutput();
                outputCH1.setDeviceSerialNumber(511546);
                outputCH1.setIsHubPortDevice(false);
                outputCH1.setChannel(1);
                
                //...for the living room light
                outputCH2 = new DigitalOutput();
                outputCH2.setDeviceSerialNumber(511546);
                outputCH2.setIsHubPortDevice(false);
                outputCH2.setChannel(2);
                
                //...for the bathroom light
                outputCH3 = new DigitalOutput();
                outputCH3.setDeviceSerialNumber(511546);
                outputCH3.setIsHubPortDevice(false);
                outputCH3.setChannel(3);
                
                //...for the dcmotor fan
                outputCH4 = new DigitalOutput();
                outputCH4.setDeviceSerialNumber(511546);
                outputCH4.setIsHubPortDevice(false);
                outputCH4.setChannel(4);
                
        } //end Sensors constructor

    /****************************************************************************
    * Method Name    : getRFIDCH method
    * Authors        : Cameron, Tonia, Shelly
    * Date           : 11/27/18
    * Course/Section : CSC 490
    * Method Description: Gets the RFID channel.
    *
    * BEGIN getRFIDCH
    *    Get RFID channel
    * END   getRFIDCH
    ****************************************************************************/
        RFID getRFIDCH() throws Exception {
                
                //local constants
                
                //local variables
                
                /********** start getRFIDCH method **********/
                
                //Get RFID channel
                return rfidCH;
                
        } //end getRFIDCH method
        
    /****************************************************************************
    * Method Name    : getDistanceCH method
    * Authors        : Cameron, Tonia, Shelly
    * Date           : 11/27/18
    * Course/Section : CSC 490
    * Method Description: Gets the Distance channel.
    *
    * BEGIN getDistanceCH
    *    Get distance channel
    * END   getDistanceCH
    ****************************************************************************/
        DistanceSensor getDistanceCH() throws Exception {
                
                //local constants
                
                //local variables
                
                /********** start getDistanceCH method **********/
                
                //Get distance channel
                return distanceCH;
                
        } //end getDistanceCH method
        
    /****************************************************************************
    * Method Name    : getHumidityCH method
    * Authors        : Cameron, Tonia, Shelly
    * Date           : 11/27/18
    * Course/Section : CSC 490
    * Method Description: Gets the Humidity channel.
    *
    * BEGIN getHumidityCH
    *    Get humidity channel
    * END   getHumidityCH
    ****************************************************************************/
        HumiditySensor getHumidityCH() throws Exception {
                
                //local constants
                
                //local variables
                
                /********** start getHumidityCH method **********/
                
                //Get humidity channel
                return humidityCH;
                
        } //end getHumidityCH method
        
    /****************************************************************************
    * Method Name    : getOutputCH method
    * Authors        : Cameron, Tonia, Shelly
    * Date           : 11/27/18
    * Course/Section : CSC 490
    * Method Description: Returns the desired InterfaceKit digital output channel.
    *
    * BEGIN getOutputCH
    *    SWITCH(Set digital output based on name)
    *       CASE(CH#): Set desired digital output channel
    *    END SWITCH
    *    Get the output channel
    * END   getOutputCH
    ****************************************************************************/
        DigitalOutput getOutputCH(String chName) throws Exception {
                
                //local constants
                final String CH0 = "CH0";          
                final String CH1 = "CH1";       
                final String CH2 = "CH2";      
                final String CH3 = "CH3";   
                final String CH4 = "CH4";              
                
                //local variables
                DigitalOutput outputCH;    //Digital output channel to be returned
                
                /********** start getOutputCH method **********/
                
                //Set digital output channel based on name
                switch(chName) {
                
                    case CH0:
                        //Get desired digital output channel
                        outputCH = outputCH0;
                        break;
                    case CH1:
                        outputCH = outputCH1;
                        break;
                    case CH2:
                        outputCH = outputCH2;
                        break;
                    case CH3:
                        outputCH = outputCH3;
                        break;
                    case CH4:
                        outputCH = outputCH4;
                        break;
                    default:
                        outputCH = null;
                        
                } //End switch
                
                //Get digital output channel
                return outputCH;
                
        } //end getOutputCH method
        
    /****************************************************************************
    * Method Name    : setUpChannels method
    * Authors        : Cameron, Tonia, Shelly
    * Date           : 11/27/18
    * Course/Section : CSC 490
    * Method Description: Calls methods to open the channels and set up the phidgets.
    *
    * BEGIN setUpChannels
    *    Call method to open the channels
    *    Call method to create attach events
    *    Call method to create detach events
    *    Call method to create error events
    *    Call method to add functionality to the phidgets
    * END   setUpChannels
    ****************************************************************************/
        void setUpChannels() throws Exception {
                
                //local constants
                
                //local variables
                
                /********** start setUpChannels method **********/
                
                //Open channels
                openChannels();
                
                //Create attach events
                createAttachEvents();
                
                //Create detach events
                createDetachEvents();
                
                //Create error listeners
                createErrorEvents();
                
                //Add functionality
                addFunctionality();
                
        } //end setUpChannels method
        
    /****************************************************************************
    * Method Name    : getData method
    * Authors        : Cameron, Tonia, Shelly
    * Date           : 11/27/18
    * Course/Section : CSC 490
    * Method Description: Gets data for the specified period of time.
    *
    * BEGIN getData
    *    Gets data for the specified period of time
    * END   getData
    ****************************************************************************/
        void getData(int timeOut) throws Exception {
                
                //local constants
                
                //local variables
                
                /********** start getData method **********/
                
                //get data
                Thread.sleep(timeOut);
                
        } //end getData method
        
    /****************************************************************************
    * Method Name    : closeChannels method
    * Authors        : Cameron, Tonia, Shelly
    * Date           : 11/27/18
    * Course/Section : CSC 490
    * Method Description: Closes all channels.
    *
    * BEGIN ControlPanel
    *    Close the channels
    * END   ControlPanel
    ****************************************************************************/
        void closeChannels() throws Exception {
                
                //local constants
                
                //local variables
                
                /********** start closeChannels method **********/
                
                //close channels
                rfidCH.close();
                distanceCH.close();
                humidityCH.close();
                outputCH0.close();
                outputCH1.close();
        outputCH2.close();
        outputCH3.close();
        outputCH4.close();
                
        } //end closeChannels method
        
    /****************************************************************************
    * Method Name    : openChannels method
    * Authors        : Cameron, Tonia, Shelly
    * Date           : 11/27/18
    * Course/Section : CSC 490
    * Method Description: Opens all channels.
    *
    * BEGIN openChannels
    *    Open RFID channel
    *    Open Distance channel
    *    Open Humidity channel
    *    Open InterfaceKit channels
    * END   openChannels
    ****************************************************************************/
        private void openChannels() throws Exception {
                
                //local constants
                
                //local variables
                
                /********** start openChannels method **********/
                
                //Open channels for
                //...RFID channel
                try {
                        rfidCH.open(5000);
                }
                catch (PhidgetException e) {
                        throw new Exception ("Program Terminated: Open Failed", e);
                }
                //...Distance channel
                try {
                    distanceCH.open(5000);
                }
                catch (PhidgetException e) {
                        throw new Exception ("Program Terminated: Open Failed", e);
                }
                //...Humidity channel
                try {
                        humidityCH.open(5000);
                }
                catch (PhidgetException e) {
                        throw new Exception ("Program Terminated: Open Failed", e);
                }
                //Open InterfaceKit channels
                //...for the RFID red light
                try {
                        outputCH0.open(5000);
                }
                catch (PhidgetException e) {
                        throw new Exception ("Program Terminated: Open Failed", e);
                }
                //...for the RFID green light
                try {
                        outputCH1.open(5000);
                }
                catch (PhidgetException e) {
                        throw new Exception ("Program Terminated: Open Failed", e);
                }
                //...for the living room light
                try {
                        outputCH2.open(5000);
                }
                catch (PhidgetException e) {
                        throw new Exception ("Program Terminated: Open Failed", e);
                }
                //...for the bathroom light
                try {
                        outputCH3.open(5000);
                }
                catch (PhidgetException e) {
                        throw new Exception ("Program Terminated: Open Failed", e);
                }
                //...for the dcmotor fan
                try {
                        outputCH4.open(5000);
                }
                catch (PhidgetException e) {
                        throw new Exception ("Program Terminated: Open Failed", e);
                }
                
        } //end openChannels method
        
    /****************************************************************************
    * Method Name    : createAttachEvents method
    * Authors        : Cameron, Tonia, Shelly
    * Date           : 11/27/18
    * Course/Section : CSC 490
    * Method Description: Creates attach events and adds them to the phidgets.
    *
    * BEGIN createAttachEvents
    *    Create sensor attach events for
    *    ...RFID sensor
    *    ...Distance sensor
    *    ...Humidity sensor
    *    Create InterfaceKit attach events for
    *    ...RFID red light
    *    ...RFID green light
    *    ...living room light
    *    ...bathroom light
    *    ...dcmotor fan
    * END   createAttachEvents
    ****************************************************************************/
        private void createAttachEvents() throws Exception {
                
                //local constants
                
                //local variables
                
                /********** start createAttachEvents method **********/
                
                //RFID sensor attach event
                rfidCH.addAttachListener(new AttachListener() {
                        public void onAttach(AttachEvent ae) {
                                try {
                                        System.out.print("\nAttach Event:");
                                        
                                        RFID ph = (RFID) ae.getSource();
                                        
                                        /**
                                        * Get device information and display it.
                                        **/
                                        int serialNumber = ph.getDeviceSerialNumber();
                                        String channelClass = ph.getChannelClassName();
                                        int channel = ph.getChannel();
                                        
                                        DeviceClass deviceClass = ph.getDeviceClass();
                                        if (deviceClass != DeviceClass.VINT) {
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Channel:  " + channel + "\n");
                                        } 
                                        else {                  
                                                int hubPort = ph.getHubPort();
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Hub Port: " + hubPort + "\n\t-> Channel:  " + channel + "\n");
                                        }
                                        
                                } 
                                catch (PhidgetException e) {
                                        System.out.println("An error has occured while attempting to attach to your RFID");
                                }
                        }
                });
                
                //Distance sensor attach event
                distanceCH.addAttachListener(new AttachListener() {
                        public void onAttach(AttachEvent ae) {
                                try {
                                        System.out.print("\nAttach Event:");
                                        
                                        DistanceSensor ph = (DistanceSensor) ae.getSource();
                                        
                                        /**
                                        * Get device information and display it.
                                        **/
                                        int serialNumber = ph.getDeviceSerialNumber();
                                        String channelClass = ph.getChannelClassName();
                                        int channel = ph.getChannel();
                                        
                                        DeviceClass deviceClass = ph.getDeviceClass();
                                        if (deviceClass != DeviceClass.VINT) {
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Channel:  " + channel + "\n");
                                        } 
                                        else {                  
                                                int hubPort = ph.getHubPort();
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Hub Port: " + hubPort + "\n\t-> Channel:  " + channel + "\n");
                                        }
                                        
                                        
                                        //Set data interval
                                        System.out.print("\tSetting DataInterval to 1000ms\n");
                                        ph.setDataInterval(1000);

                                        //Set change trigger
                                        System.out.print("\tSetting Distance ChangeTrigger to 0.0\n");
                                        ph.setDistanceChangeTrigger(0);
                                        
                                } 
                                catch (PhidgetException e) {
                                        System.out.println("An error has occured while attempting to attach to your Distance Sensor");
                                }
                        }
                });
                
                //Humidity sensor attach event
                humidityCH.addAttachListener(new AttachListener() {
                        public void onAttach(AttachEvent ae) {
                                try {
                                        System.out.print("\nAttach Event:");
                                        
                                        HumiditySensor ph = (HumiditySensor) ae.getSource();
                                        
                                        /**
                                        * Get device information and display it.
                                        **/
                                        int serialNumber = ph.getDeviceSerialNumber();
                                        String channelClass = ph.getChannelClassName();
                                        int channel = ph.getChannel();
                                        
                                        DeviceClass deviceClass = ph.getDeviceClass();
                                        if (deviceClass != DeviceClass.VINT) {
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Channel:  " + channel + "\n");
                                        } 
                                        else {                  
                                                int hubPort = ph.getHubPort();
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Hub Port: " + hubPort + "\n\t-> Channel:  " + channel + "\n");
                                        }
                                        
                                        
                                        //Set data interval
                                        System.out.print("\tSetting DataInterval to 1000ms\n");
                                        ph.setDataInterval(1000);

                                        //Set change trigger
                                        System.out.print("\tSetting Humidity ChangeTrigger to 0.0\n");
                                        ph.setHumidityChangeTrigger(0.0);
                                        
                                } 
                                catch (PhidgetException e) {
                                        System.out.println("An error has occured while attempting to attach to your Humidity Sensor");
                                }
                        }
                });
                
                //InterfaceKit attach event for the RFID red light
        outputCH0.addAttachListener(new AttachListener() {
                        public void onAttach(AttachEvent ae) {
                                try {
                                        System.out.print("\nAttach Event:");
                                        
                                        DigitalOutput ph = (DigitalOutput) ae.getSource();
                                        
                                        /**
                                        * Get device information and display it.
                                        **/
                                        int serialNumber = ph.getDeviceSerialNumber();
                                        String channelClass = ph.getChannelClassName();
                                        int channel = ph.getChannel();
                                        
                                        DeviceClass deviceClass = ph.getDeviceClass();
                                        if (deviceClass != DeviceClass.VINT) {
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Channel:  " + channel + "\n");
                                        } 
                                        else {            
                                                int hubPort = ph.getHubPort();
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Hub Port: " + hubPort + "\n\t-> Channel:  " + channel + "\n");
                                        }
                                } 
                                catch (PhidgetException e) {
                                        System.out.println("error detach event");
                                }
                        }
        });
        
        //InterfaceKit attach event for the RFID green light
        outputCH1.addAttachListener(new AttachListener() {
                        public void onAttach(AttachEvent ae) {
                                try {
                                        System.out.print("\nAttach Event:");
                                        
                                        DigitalOutput ph = (DigitalOutput) ae.getSource();
                                        
                                        /**
                                        * Get device information and display it.
                                        **/
                                        int serialNumber = ph.getDeviceSerialNumber();
                                        String channelClass = ph.getChannelClassName();
                                        int channel = ph.getChannel();
                                        
                                        DeviceClass deviceClass = ph.getDeviceClass();
                                        if (deviceClass != DeviceClass.VINT) {
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Channel:  " + channel + "\n");
                                        } 
                                        else {            
                                                int hubPort = ph.getHubPort();
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Hub Port: " + hubPort + "\n\t-> Channel:  " + channel + "\n");
                                        }
                                } 
                                catch (PhidgetException e) {
                                        System.out.println("error detach event");
                                }
                        }
        });
        
        //InterfaceKit attach event for the living room light
        outputCH2.addAttachListener(new AttachListener() {
                        public void onAttach(AttachEvent ae) {
                                try {
                                        System.out.print("\nAttach Event:");
                                        
                                        DigitalOutput ph = (DigitalOutput) ae.getSource();
                                        
                                        /**
                                        * Get device information and display it.
                                        **/
                                        int serialNumber = ph.getDeviceSerialNumber();
                                        String channelClass = ph.getChannelClassName();
                                        int channel = ph.getChannel();
                                        
                                        DeviceClass deviceClass = ph.getDeviceClass();
                                        if (deviceClass != DeviceClass.VINT) {
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Channel:  " + channel + "\n");
                                        } 
                                        else {            
                                                int hubPort = ph.getHubPort();
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Hub Port: " + hubPort + "\n\t-> Channel:  " + channel + "\n");
                                        }
                                } 
                                catch (PhidgetException e) {
                                        System.out.println("error detach event");
                                }
                        }
        });
        
        //InterfaceKit attachEvent for the bathroom light
        outputCH3.addAttachListener(new AttachListener() {
                        public void onAttach(AttachEvent ae) {
                                try {
                                        System.out.print("\nAttach Event:");
                                        
                                        DigitalOutput ph = (DigitalOutput) ae.getSource();
                                        
                                        /**
                                        * Get device information and display it.
                                        **/
                                        int serialNumber = ph.getDeviceSerialNumber();
                                        String channelClass = ph.getChannelClassName();
                                        int channel = ph.getChannel();
                                        
                                        DeviceClass deviceClass = ph.getDeviceClass();
                                        if (deviceClass != DeviceClass.VINT) {
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Channel:  " + channel + "\n");
                                        } 
                                        else {            
                                                int hubPort = ph.getHubPort();
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Hub Port: " + hubPort + "\n\t-> Channel:  " + channel + "\n");
                                        }
                                } 
                                catch (PhidgetException e) {
                                        System.out.println("error detach event");
                                }
                        }
        });
        
        //InterfaceKit attach event for the dcmotor fan
        outputCH4.addAttachListener(new AttachListener() {
                        public void onAttach(AttachEvent ae) {
                                try {
                                        System.out.print("\nAttach Event:");
                                        
                                        DigitalOutput ph = (DigitalOutput) ae.getSource();
                                        
                                        /**
                                        * Get device information and display it.
                                        **/
                                        int serialNumber = ph.getDeviceSerialNumber();
                                        String channelClass = ph.getChannelClassName();
                                        int channel = ph.getChannel();
                                        
                                        DeviceClass deviceClass = ph.getDeviceClass();
                                        if (deviceClass != DeviceClass.VINT) {
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Channel:  " + channel + "\n");
                                        } 
                                        else {            
                                                int hubPort = ph.getHubPort();
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Hub Port: " + hubPort + "\n\t-> Channel:  " + channel + "\n");
                                        }
                                } 
                                catch (PhidgetException e) {
                                        System.out.println("error detach event");
                                }
                        }
        });
                
        } //end createAttachEvents method
        
    /****************************************************************************
    * Method Name    : createDetachEvents method
    * Authors        : Cameron, Tonia, Shelly
    * Date           : 11/27/18
    * Course/Section : CSC 490
    * Method Description: Creates detach events and adds them to the phidgets.
    *
    * BEGIN createDetachEvents
    *    Create sensor detach events for
    *    ...RFID sensor
    *    ...Distance sensor
    *    ...Humidity sensor
    *    Create InterfaceKit detach events for
    *    ...RFID red light
    *    ...RFID green light
    *    ...living room light
    *    ...bathroom light
    *    ...dcmotor fan
    * END   createDetachEvents
    ****************************************************************************/
        private void createDetachEvents() throws Exception {
                
                //local constants
                
                //local variables
                
                /********** start createDetachEvents method **********/
                
                //RFID sensor detach event
                rfidCH.addDetachListener(new DetachListener() {
                        public void onDetach(DetachEvent de) {
                                try {
                                        System.out.print("\nAttach Event:");
                                        
                                        Phidget ph = de.getSource();
                                        
                                        /**
                                        * Get device information and display it.
                                        **/
                                        int serialNumber = ph.getDeviceSerialNumber();
                                        String channelClass = ph.getChannelClassName();
                                        int channel = ph.getChannel();
                                        
                                        DeviceClass deviceClass = ph.getDeviceClass();
                                        if (deviceClass != DeviceClass.VINT) {
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Channel:  " + channel + "\n");
                                        } 
                                        else {                  
                                                int hubPort = ph.getHubPort();
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Hub Port: " + hubPort + "\n\t-> Channel:  " + channel + "\n");
                                        }
                                } 
                                catch (PhidgetException e) {
                                        System.out.println("Error in detaching to the RFID sensor");
                                }
                        }
                });
                
                //Humidity sensor detach event
                humidityCH.addDetachListener(new DetachListener() {
                        public void onDetach(DetachEvent de) {
                                try {
                                        System.out.print("\nAttach Event:");
                                        
                                        Phidget ph = de.getSource();
                                        
                                        /**
                                        * Get device information and display it.
                                        **/
                                        int serialNumber = ph.getDeviceSerialNumber();
                                        String channelClass = ph.getChannelClassName();
                                        int channel = ph.getChannel();
                                        
                                        DeviceClass deviceClass = ph.getDeviceClass();
                                        if (deviceClass != DeviceClass.VINT) {
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Channel:  " + channel + "\n");
                                        } 
                                        else {                  
                                                int hubPort = ph.getHubPort();
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Hub Port: " + hubPort + "\n\t-> Channel:  " + channel + "\n");
                                        }
                                } 
                                catch (PhidgetException e) {
                                        System.out.println("Error Detaching to the Humidity Sensor");
                                }
                        }
                });
                
                //Distance sensor detach event
                distanceCH.addDetachListener(new DetachListener() {
                        public void onDetach(DetachEvent de) {
                                try {
                                        System.out.print("\nAttach Event:");
                                        
                                        Phidget ph = de.getSource();
                                        
                                        /**
                                        * Get device information and display it.
                                        **/
                                        int serialNumber = ph.getDeviceSerialNumber();
                                        String channelClass = ph.getChannelClassName();
                                        int channel = ph.getChannel();
                                        
                                        DeviceClass deviceClass = ph.getDeviceClass();
                                        if (deviceClass != DeviceClass.VINT) {
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Channel:  " + channel + "\n");
                                        } 
                                        else {                  
                                                int hubPort = ph.getHubPort();
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Hub Port: " + hubPort + "\n\t-> Channel:  " + channel + "\n");
                                        }
                                } 
                                catch (PhidgetException e) {
                                        System.out.println("Error Detaching to the Distance Sensor");
                                }
                        }
                });
                
                //InterfaceKit detach event for the RFID red light
        outputCH0.addDetachListener(new DetachListener() {
                        public void onDetach(DetachEvent de) {
                                try {
                                        System.out.print("\nAttach Event:");
                                        
                                        Phidget ph = de.getSource();
                                        
                                        /**
                                        * Get device information and display it.
                                        **/
                                        int serialNumber = ph.getDeviceSerialNumber();
                                        String channelClass = ph.getChannelClassName();
                                        int channel = ph.getChannel();
                                        
                                        DeviceClass deviceClass = ph.getDeviceClass();
                                        if (deviceClass != DeviceClass.VINT) {
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Channel:  " + channel + "\n");
                                        } 
                                        else {            
                                                int hubPort = ph.getHubPort();
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Hub Port: " + hubPort + "\n\t-> Channel:  " + channel + "\n");
                                        }
                                } 
                                catch (PhidgetException e) {
                                        System.out.println("error detach event");
                                }
                        }
        });
        
        //InterfaceKit detach event for the RFID green light
        outputCH1.addDetachListener(new DetachListener() {
                        public void onDetach(DetachEvent de) {
                                try {
                                        System.out.print("\nAttach Event:");
                                        
                                        Phidget ph = de.getSource();
                                        
                                        /**
                                        * Get device information and display it.
                                        **/
                                        int serialNumber = ph.getDeviceSerialNumber();
                                        String channelClass = ph.getChannelClassName();
                                        int channel = ph.getChannel();
                                        
                                        DeviceClass deviceClass = ph.getDeviceClass();
                                        if (deviceClass != DeviceClass.VINT) {
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Channel:  " + channel + "\n");
                                        } 
                                        else {            
                                                int hubPort = ph.getHubPort();
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Hub Port: " + hubPort + "\n\t-> Channel:  " + channel + "\n");
                                        }
                                } 
                                catch (PhidgetException e) {
                                        System.out.println("error detach event");
                                }
                        }
        });
        
        //InterfaceKit detach event for the living room light
        outputCH2.addDetachListener(new DetachListener() {
                        public void onDetach(DetachEvent de) {
                                try {
                                        System.out.print("\nAttach Event:");
                                        
                                        Phidget ph = de.getSource();
                                        
                                        /**
                                        * Get device information and display it.
                                        **/
                                        int serialNumber = ph.getDeviceSerialNumber();
                                        String channelClass = ph.getChannelClassName();
                                        int channel = ph.getChannel();
                                        
                                        DeviceClass deviceClass = ph.getDeviceClass();
                                        if (deviceClass != DeviceClass.VINT) {
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Channel:  " + channel + "\n");
                                        } 
                                        else {            
                                                int hubPort = ph.getHubPort();
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Hub Port: " + hubPort + "\n\t-> Channel:  " + channel + "\n");
                                        }
                                } 
                                catch (PhidgetException e) {
                                        System.out.println("error detach event");
                                }
                        }
        });
        
        //InterfaceKit detach event for the bathroom light
        outputCH3.addDetachListener(new DetachListener() {
                        public void onDetach(DetachEvent de) {
                                try {
                                        System.out.print("\nAttach Event:");
                                        
                                        Phidget ph = de.getSource();
                                        
                                        /**
                                        * Get device information and display it.
                                        **/
                                        int serialNumber = ph.getDeviceSerialNumber();
                                        String channelClass = ph.getChannelClassName();
                                        int channel = ph.getChannel();
                                        
                                        DeviceClass deviceClass = ph.getDeviceClass();
                                        if (deviceClass != DeviceClass.VINT) {
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Channel:  " + channel + "\n");
                                        } 
                                        else {            
                                                int hubPort = ph.getHubPort();
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Hub Port: " + hubPort + "\n\t-> Channel:  " + channel + "\n");
                                        }
                                } 
                                catch (PhidgetException e) {
                                        System.out.println("error detach event");
                                }
                        }
        });
        
        //IntefaceKit detach event for the dcmotor fan
        outputCH4.addDetachListener(new DetachListener() {
                        public void onDetach(DetachEvent de) {
                                try {
                                        System.out.print("\nAttach Event:");
                                        
                                        Phidget ph = de.getSource();
                                        
                                        /**
                                        * Get device information and display it.
                                        **/
                                        int serialNumber = ph.getDeviceSerialNumber();
                                        String channelClass = ph.getChannelClassName();
                                        int channel = ph.getChannel();
                                        
                                        DeviceClass deviceClass = ph.getDeviceClass();
                                        if (deviceClass != DeviceClass.VINT) {
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Channel:  " + channel + "\n");
                                        } 
                                        else {            
                                                int hubPort = ph.getHubPort();
                                                System.out.print("\n\t-> Channel Class: " + channelClass + "\n\t-> Serial Number: " + serialNumber +
                                                          "\n\t-> Hub Port: " + hubPort + "\n\t-> Channel:  " + channel + "\n");
                                        }
                                } 
                                catch (PhidgetException e) {
                                        System.out.println("error detach event");
                                }
                        }
        });
                
        } //end createDetachEvents method
        
    /****************************************************************************
    * Method Name    : createErrorEvents method
    * Authors        : Cameron, Tonia, Shelly
    * Date           : 11/27/18
    * Course/Section : CSC 490
    * Method Description: Creates error events and adds them to the phidgets.
    *
    * BEGIN createErrorEvents
    *    Create sensor error events for
    *    ...RFID sensor
    *    ...Distance sensor
    *    ...Humidity sensor
    *    Create InterfaceKit error events for
    *    ...RFID red light
    *    ...RFID green light
    *    ...living room light
    *    ...bathroom light
    *    ...dcmotor fan
    * END   createErrorEvents
    ****************************************************************************/
        private void createErrorEvents() throws Exception {
                
                //local constants
                
                //local variables
                
                /********** start createErrorEvents method **********/
                
                //RFID sensor error listener
                rfidCH.addErrorListener(new ErrorListener() {
                        public void onError(ErrorEvent ee) {
                                System.out.println("Error: " + ee.getDescription());
                        }
                });
                
                //Distance sensor error listener
                distanceCH.addErrorListener(new ErrorListener() {
                        public void onError(ErrorEvent ee) {
                                System.out.println("Error: " + ee.getDescription());
                        }
                });
                
                //Humidity sensor error listener 
                humidityCH.addErrorListener(new ErrorListener() {
                        public void onError(ErrorEvent ee) {
                                System.out.println("Error: " + ee.getDescription());
                        }
                });
                
                //InterfaceKit error listener for RFID red light
        outputCH0.addErrorListener(new ErrorListener() {
                        public void onError(ErrorEvent ee) {
                                System.out.println("Error: " + ee.getDescription());
                        }
                });

        //InterfaceKit error listener for RFID green light
        outputCH1.addErrorListener(new ErrorListener() {
                        public void onError(ErrorEvent ee) {
                                System.out.println("Error: " + ee.getDescription());
                        }
                });
        
        //InterfaceKit error listener for living room light
        outputCH2.addErrorListener(new ErrorListener() {
                        public void onError(ErrorEvent ee) {
                                System.out.println("Error: " + ee.getDescription());
                        }
                });
        
        //InterfaceKit error listener for bathroom light
        outputCH3.addErrorListener(new ErrorListener() {
                        public void onError(ErrorEvent ee) {
                                System.out.println("Error: " + ee.getDescription());
                        }
                });
        
        //InterfaceKit error listener for dcmotor fan
        outputCH4.addErrorListener(new ErrorListener() {
                        public void onError(ErrorEvent ee) {
                                System.out.println("Error: " + ee.getDescription());
                        }
                });
                
        } //end createErrorEvents method
        
    /****************************************************************************
    * Method Name    : addFunctionality method
    * Authors        : Cameron, Tonia, Shelly
    * Date           : 11/27/18
    * Course/Section : CSC 490
    * Method Description: Creates listeners for unique events with each sensor.
    *
    * BEGIN addFunctionality
    *    Set up RFID sensor
    *    Set up Distance sensor
    *    Set up Humidity sensor
    * END   addFunctionality
    ****************************************************************************/
        private void addFunctionality() throws Exception {
                
                //local constants
                
                //local variables
                
                /********** start addFunctionality method **********/
                
                //RFID Sensor
                //Triggered when the RFID reads a card ID
                rfidCH.addTagListener(new RFIDTagListener() {
                        public void onTag(RFIDTagEvent e) {
                                
                                System.out.println("[Tag Event] -> Tag: " + e.getTag());
                        }
                });
                
                //RFID Sensor
                //Triggered when the RFID can't read the card ID anymore
                rfidCH.addTagLostListener(new RFIDTagLostListener() {
                        public void onTagLost(RFIDTagLostEvent e) {
                                
                                System.out.println("[Tag Lost Event] -> Tag: " + e.getTag());
                                if(e.getTag().equals("5f00d0a761")||e.getTag().equals("5f00d0c5ce")) {
                                        rfidPositiveFlag = true;
                                }
                                else
                                        rfidNegativeFlag = true;
                        }
                });
                
                //Distance sensor
                //Triggered when the distance sensor detects something passes in front of the sensor
                distanceCH.addDistanceChangeListener(new DistanceSensorDistanceChangeListener() {
                        public void onDistanceChange(DistanceSensorDistanceChangeEvent e) {

                                System.out.println("[Distance Event] -> Distance: " + e.getDistance());
                                if(!distanceSet) {
                                        baseDistance = e.getDistance();
                                        distanceSet  = true;
                                }
                                
                                if(e.getDistance() < baseDistance - 3)
                                        distanceFlag = true;
                        }
                });
                
                //Humidity sensor
                //Triggered when the humidity changes
                humidityCH.addHumidityChangeListener(new HumiditySensorHumidityChangeListener() {
                        public void onHumidityChange(HumiditySensorHumidityChangeEvent e) {
                                //System.out.println("[Humidity Event] -> Humidity: " + e.getHumidity());
                                if(e.getHumidity() > maxHumidity) {
                                        humidityFlag = true; 
                                }
                                
                        }
                });
                
        } //end addFunctionality method
        
} //end Sensors class