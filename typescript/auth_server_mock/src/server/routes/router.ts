import express from "express";

const router = express.Router();
const authUrl = '/oauth/authorize'

router.get(authUrl, (req, res) => {
    const parms = req.query;
    const rand = 'dafdsjafjasfjklaewj';
    const responseObj = {
        code: rand,
        state: parms.state
    }
    res.send(responseObj);
    console.log(responseObj);

})

function randomString(length: number): string {
    const _length = length;
    return 'aaa';
}

export default router;