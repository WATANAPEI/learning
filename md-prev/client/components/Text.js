import React, { Component } from 'react'
import Typography from '@material-ui/core/Typography'
import {withStyles} from '@material-ui/core/styles'
import Card from '@material-ui/core/Card'
import CardContent from '@material-ui/core/CardContent'
import CardActions from '@material-ui/core/CardActions'
import NewQuote from '../containers/NewQuote'
import TweetQuote from './TweetQuote'
import styled from 'styled-components';
import { spacing, palette } from '@material-ui/system';

const Box = styled.div`${spacing}${palette}`;

const styles = themes => ( {
    TextDisplay: {
        maxWidth: 800,
        maxheight: 500,
        margin: "100px auto",
        //padding: "${theme.spacing.unit * 8}px 0 ${theme.spacing.unit * 6}px",
        padding: "20px 0 20px",
    },
    BoxArea: {
        width: '100%',
        display: "flex",
        flexDirection: "row",
    },
    TweetButton: {
        
    },
    NewQuoteArea:{
        marginLeft: 'auto',
    },
    NewQuoteButton: {
        
    }
});

//Textをconstからclassにしてconstructorで渡せるようにする
//さらにWithStylesをTextクラスに適用する
//この時の引数の渡し方は変わらずrender()の中でよい
class Text extends Component {

    constructor(props){
        super(props)
    };

    render(){

    const {text, author, classes} = this.props;
    return(
    <Card className={classes.TextDisplay} id="tweet-display">
        <CardContent>
            <div id="text">
                <Typography align="center" variant="h3">{text}</Typography>
            </div>
            <div id="author">
                <Typography align="center" variant="h5">{author}</Typography>
            </div>
        </CardContent>
        <CardActions>
            <div className={classes.BoxArea}>
                <div>
                    <TweetQuote className={classes.TweetButton}/>
                </div>
                <div className={classes.NewQuoteArea}>
                    <NewQuote className={classes.NewQuoteButton}/>
                </div>
            </div>
        </CardActions>
    </Card>
    )
    }
}
export default withStyles(styles)(Text)
