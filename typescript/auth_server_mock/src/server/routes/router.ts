import express from "express";
import crypto from "crypto";

const router = express.Router();
const authUrl = '/oauth/authorize'

//testuri: http://localhost:8081/oauth/authorize?redirect_uri=https%3A%2F%2Fwww.google.com%2F%3Fhl%3Dja&state=sflajsfkjaksjfdsaf&client_id=test&scope=notify&response_type=code

router.get(authUrl, (req, res) => {
    const params = req.query;
    if(params.redirect_uri === undefined) {
        res.status(400).send("Bad Request");
        console.log(`redirect uri is not specified.`);
        return;
    } else if(params.state === undefined || params.client_id === undefined || params.scope !== 'notify') {
        const error = 'invalid_request';
        const error_description = params.scope;
        const redirect_uri = params.redirect_uri + '?error=' + error + '&error_description=' + error_description;
        res.redirect(redirect_uri);
        return;
    }
    const code = randomString(20);
    const state = params.state;
    const redirect_uri = params.redirect_uri + '?code=' + code + '&state=' + state;
    res.redirect(redirect_uri);
    console.log(`redirected to ${redirect_uri}`);
})

function randomString(charLength: number): string {
    return  crypto.randomBytes(charLength / 2).toString('hex');
}

export default router;