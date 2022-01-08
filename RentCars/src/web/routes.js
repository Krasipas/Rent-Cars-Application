import { config } from './config/appConfig.js';
import fetch from 'node-fetch';
import axios from 'axios';

export default function(app){

    // User
    app.get('/', (req, res) => 
    {
        fetch(`${config.API}/user/fetch`)
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

    app.get('/user/add', (req, res) => 
    {
        res.render('addUser.ejs')
    })

    app.post('/user/add', (req, res) => 
    {
        axios
            .post(`${config.API}/user/save`, {
                firstName: req.body.firstName,
                lastName: req.body.lastName,
                num : req.body.num,
                city : req.body.city,
                birthDate : req.body.birthDate,
                isManager : req.body.isManager
            })
            .then(res => {
                console.log(`statusCode: ${res.status}`)
            })
            .catch(error => {
                console.error(error)
                res.send(error);
            })
        res.redirect("/");
    });

    app.post('/user/find', (req, res) => 
    {
        //console.log(req.body);
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
        fetch(config.API + '/car/fetch')
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

    app.post('/car/add', (req, res) => 
    {
        axios
            .post(`${config.API}/car/save`, {
                brand: req.body.brand,
                model: req.body.model,
                dateManufactured : req.body.dateManufactured,
                fuel : req.body.fuel,
                category : req.body.category,
                registrationNum : req.body.registrationNum
            })
            .then(res => {
                console.log(`statusCode: ${res.status}`)
            })
            .catch(error => {
                console.error(error)
                res.send(error);
            })
        res.redirect("/cars");
    });

    app.post('/car/find/brand', (req, res) => 
    {
        //console.log(req.body);
        fetch(`${config.API}/car/find/brand?brand=${req.body.brand}`)
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
                //console.log(data);
                res.render('users.ejs', {
                    data: data.users
                });
            })
            .catch(err => {
                console.log(err)
                res.send("Error!");
            })
    })

    // UserCars

    app.get('/rent/', (req, res) => 
    {
        fetch(`${config.API}/rent/fetch`)
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

    app.get('/rent/rent', (req, res) => 
    {
        res.render('addUserCar.ejs')
    })

    app.post('/rent/add', (req, res) => 
    {   
        const days = parseInt(req.body.daysToBeUsed);
        if(! days)
            res.send("Invalid day");

        axios
            .post(`${config.API}/rent/rent`, {
                userNum: req.body.userNum,
                carNum: req.body.carNum,
                daysToBeUsed : days
            })
            .then(res => {
                //console.log(`statusCode: ${res.status}`)
            })
            .catch(error => {
                console.error(error)
            })
        res.redirect("/rent");
    });
}