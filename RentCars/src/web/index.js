import express from 'express';
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

import routes from './routes.js';
routes(app);


app.listen(config.PORT, () => 
{
    console.log(`Web app listening at port: ${config.PORT}`)
})