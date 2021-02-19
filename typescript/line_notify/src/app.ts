import router from "./routes/router";
import express from "express";

let app = express();
let authEndPoint = "https://notify-bot.line.me/oauth/authorize";
let tokenEndPoint = "https://notify-bot.line.me/oauth/token";

app.use("/", router);

app.listen(8080, () => {console.log(`Waiting at port 8080.`)});

export default app;