const React = require("react");
const ReactPlayer = require("react-player").default


module.exports = class AudioPlayer extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className={"audio-player"}>
                <h3 className={"current-track"}>
                    {this.props.song.title || this.props.song.fileName || "Select track"}
                </h3>
                <ReactPlayer config={{
                    file: {
                        attributes: {
                            crossOrigin: "anonymous"
                        },
                        forceAudio: true
                    }
                }}
                             controls={true}
                             url={this.props.root + this.props.song.fileName}
                             width={"90%"} height={"3rem"}></ReactPlayer>
            </div>
        )
    }
}
