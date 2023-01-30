
//User location
$.getJSON('http://ip-api.com/json', function(data){
var lat = data.lat;
var lon = data.lon;
var units = "metric";

$("#city").html(data.city + ", " + data.country)
console.log(data);
//Open weather API request
$.getJSON('http://api.openweathermap.org/data/2.5/weather?lat=' + lat + '&lon=' + lon + '&units=' + units + '&APPID=e95d958a11128b11ad3eb0fa101dae38', function(json){
console.log(json);
$("#temperature-celcius").html(json.main.temp + ' C&deg');
$("#temperature-farenheit").html((json.main.temp * 1,8 + 32) + ' F&deg');
$("#humidity").html(json.main.humidity + ' %');
$("#icon").html('<img src="http://openweathermap.org/img/w/' + json.weather[0].icon + '.png"</img>');
});
});
