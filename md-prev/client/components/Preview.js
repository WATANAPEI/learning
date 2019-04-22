import React, {Component} from 'react'
import marked from 'marked'

class Preview extends Component {
    constructor(props){
        super(props);
    };

    getMarkdownText(){
        var rawMarkup = marked(this.props.text, {sanitize: true});
        return { __html: rawMarkup};
    }

    render(){
    return (
        <div id="preview" dangerouslySetInnerHTML={this.getMarkdownText()} />
    )
    }
}

export default Preview
