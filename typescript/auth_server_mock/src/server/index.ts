import express from 'express';
import router from './routes/router';

const app = express();
const port = 8081;

// app.get("/", (req, res) => {
//     res.send({test: 'json'});
// })

app.use(router)

app.listen(port, () => {console.log(`Auth server is Waiting at port 8081.`)});
