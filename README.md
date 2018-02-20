My Edit Project 
Original project 
https://github.com/vasani-arpit/cordova-plugin-downloadmanager

# cordova-plugin-downloadmanager
A Cordova plugin to download file in system's default download manager

## Supported Platforms

 - Android (SDK >= 11)

 ## Installation

 ```
 cordova plugin add https://github.com/tolgatasci/cordova-downloader
 ```

 ## How to Use 

 ```
 //once device is ready
var fail = function (message) {    
    alert(message)
}
var success = function (data) {
        console.log("succes");
}
cordova.plugins.DownloadManager.download("Your URL to download","file_name.mp3", success, fail);
 ```

## Result

![screenshot](./screenshot/downloadplugin.gif)

## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request
