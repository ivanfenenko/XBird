# XBird

This is Xbird code challange. 

It contains a project with library and demo module. Library exposes public static method to get records from databas `FallDetectorLog.getData()`.
Library has a Service that is bound to notification and detects freefall when running. When freefall is detected a new record is added and aditional notification is being displayed. 

Original idea was to find some open source solution to the problem and try to translate it to Kotlin. But after reading documentation for accelerometer sensor it became clear that it should be pretty simple as angular speed (sqrt(x*x+y*y+z*z)) was around 9.8ms in idling position. That means when value becomes close to 0 it's a freefall and when phone hits the ground it becomes more then 9.8. 

So I created a simple state machine solution that moves to ACTION state as soon as it receives close to 0 value and returns a record when value becomes high again and function transitions to idle again. 

The most challanging part in the end was dependency injection and testability. As library project doesn't have a application calss it was hard to quickly come up with a good approach of injecting dependencies. 

Second issue revield itself in the end when I figured out that AccelerometerCallback has some non mockable dependencies which means I have to wrap it in an interface (which I didn't do as of time limitation but in real life scenario this is not an excuse).

Enjoy 

