import React, {Component} from 'react'

class Preview extends Component {
    constructor(props){
        super(props)
    };

    render(){
        const {text} = this.props;
    return (
        <div id="preview">
          <p>{text}</p>
        </div>
    )
    }
}

export default Preview
