import React, {Component} from 'react'

class Preview extends Component {
    constructor(props){
        super(props)
    };

    return (
        <div id="preview">
          <p>{this.props.text}</p>
        </div>
    )
}

export default Preview
