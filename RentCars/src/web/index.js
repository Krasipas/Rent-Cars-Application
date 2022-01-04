import express from 'express';
import fetch from 'node-fetch';
import { fileURLToPath } from 'url';
import { dirname } from 'path';
import { config } from './config/appConfig.js';

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);
const app = express()

app.use(express.static(__dirname + '/static'));
app.set('view engine', 'ejs');
app.use(express.urlencoded({
    extended: true
}))

// User

app.get('/', (req, res) => 
{
    fetch(`${config.API}/user/fetch`)
        .then(response => response.json())
        .then(data => {
            res.render('users.ejs', {
                data    
            });
        })
        .catch(err => {
            console.log(err)
            res.send("Error!");
        })
})

app.get('/user/add', (req, res) => 
{
    res.render('addUser.ejs')
})

app.post('/user/add', (req, res) => 
{
    // TODO
    console.log(req.body);
})

app.post('/user/find', (req, res) => 
{
    console.log(req.body);

    fetch(`${config.API}/user/find/name?fName=${req.body.fName}&lName=${req.body.lName}`)
        .then(response => response.json())
        .then(data => {
            res.render('users.ejs', {
                data    
            });
        })
        .catch(err => {
            // User not found
            res.render('users.ejs', {
                data: []    
            });
        })
})

// Car

app.get('/cars', (req, res) => 
{
    fetch(config.API + '/cars/fetch')
        .then(response => response.json())
        .then(data => {
            res.render('cars.ejs', {
                data    
            });
        })
        .catch(err => {
            console.log(err)
            res.send("Error!");
        })
})

app.get('/car/add', (req, res) => 
{
    res.render('addCar.ejs')
})

app.post('/car/find/brand', (req, res) => 
{
    console.log(req.body);

    fetch(`${config.API}/user/find/brand?Brand=${req.body.brand}`)
        .then(response => response.json())
        .then(data => {
            res.render('cars.ejs', {
                data    
            });
        })
        .catch(err => {
            // Car not found
            res.render('cars.ejs', {
                data: []    
            });
        })
})

// City

app.get('/cities', (req, res) => 
{
    fetch(config.API + '/city/fetch')
        .then(response => response.json())
        .then(data => {
            res.render('cities.ejs', {
                data    
            });
        })
        .catch(err => {
            console.log(err)
            res.send("Error!");
        })
})

app.get('/city/add', (req, res) => 
{
    res.render('addCity.ejs')
})

app.get('/city/users/:cityName', (req, res) => 
{

    fetch(`${config.API}/city/users?cityName=${req.params.cityName}`)
        .then(response => response.json())
        .then(data => {
            console.log(data);

            res.render('users.ejs', {
                data    
            });
        })
        .catch(err => {
            console.log(err)
            res.send("Error!");
        })
})

// userCars

app.get('/userCars', (req, res) => 
{
    fetch(`${config.API}/userCars/fetch`)
        .then(response => response.json())
        .then(data => {
            res.render('userCars.ejs', {
                data    
            });
        })
        .catch(err => {
            console.log(err)
            res.send("Error!");
        })
})


app.listen(config.PORT, () => 
{
    console.log(`Web app listening at port: ${config.PORT}`)
})