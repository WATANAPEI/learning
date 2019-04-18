import React from 'react';
import {withStyles} from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';

const styles = {
    root: {
        flexGrow: 1
    },
};

function HeaderBar(props){
    const {classes} = props;

    return (
        <div className={classes.root}>
            <AppBar color="default" position="static">
                <Toolbar>
                    <Typography variant="h6" color="inherit">
                        My MarkDown Editor and Previewer
                    </Typography>
                </Toolbar>
            </AppBar>
        </div>
    );
}

export default withStyles(styles)(HeaderBar)

