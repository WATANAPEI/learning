import React, { Component } from 'react'
import Typography from '@material-ui/core/Typography'
import {withStyles} from '@material-ui/core/styles'

const styles = themes => ( {
    TextDisplay: {
        maxWidth: 600,
        margin: "0 auto",
        //padding: "${theme.spacing.unit * 8}px 0 ${theme.spacing.unit * 6}px",
        padding: "20px 0 20px",
    },
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
    <div className={classes.TextDisplay} id="tweet-display">
        <div id="text">
            <Typography align="center" variant="h3">{text}</Typography>
        </div>
        <div id="author">
            <Typography align="center" variant="h5">{author}</Typography>
        </div>
    </div>
    )
    }
}
export default withStyles(styles)(Text)