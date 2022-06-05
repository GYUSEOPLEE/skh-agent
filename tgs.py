import serial
port = "/dev/ttyUSB0"

def parseGPS(data):
    
    #print "raw:", data #prints raw data
    #print(data)
    da = str(data)
    
    if len(da) > 5 and da[2:8] == "$GPRMC":
        point = decode(da)
        print("latitude : %0.6f" % point[0])
        print("longitude : %0.6f" % point[1])
        writePoint(point)

def decode(data):
    latitude = float(data[22:31]) / 100
    longitude = float(data[34:44]) / 100
    point = [latitude, longitude]
    
    return point
    
def writePoint(point):
    fp = open("/home/pi/location.txt", 'w')

    fp.write("{}, {}".format(point[0], point[1]))
    
    fp.close()

print ("Receiving GPS data")
ser = serial.Serial(port, baudrate = 9600, timeout = 0.5)

def main():
    while True:
        data = ser.readline()
        parseGPS(data)
