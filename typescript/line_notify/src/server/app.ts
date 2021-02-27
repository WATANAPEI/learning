import router from "./routes/router";
import express from "express";

let app = express();

app.use("/", router);

app.listen(8080, () => {console.log(`Waiting at port 8080.`)});

export default app;