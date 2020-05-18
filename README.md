# Animated Collapsible AppBar

A small library which helps to animate app bar without `MotionLayout`. 
This works was enspired with one of talks from Google I/O back to 2018 and now works for 2 years in one of my app.

You can achieve this result in few easy steps:

<img src='https://github.com/yamsergey/animated-collapsible-appbar-example/blob/master/app/demo.gif' width=300 />

The example app is there https://github.com/yamsergey/animated-collapsible-appbar-example

## Known issues
1. The height of the constraints have to be the same 
2. Doesn't work really well in all situations with DataBinding (There are some work arounds)

## How to install

Add Jitpack repo
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Add dependency
```
	dependencies {
	        implementation 'com.github.yamsergey:animated-collapsible-appbar:Tag'
	}
```

## How to use
https://medium.com/@yamsergey/animated-app-bar-with-constraintlayout-d665b0234689
