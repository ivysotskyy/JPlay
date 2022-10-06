const React = require("react");
const {createRoot} = require("react-dom/client");
const client = require("./client");

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            audioTracks: []
        };
    }

    componentDidMount() {
        client({
            method: "GET", path: "/api/audioTracks"
        }).then(response => {
            console.log(response.entity._embedded.audioTracks);
            this.setState({
                audioTracks: response.entity._embedded.audioTracks
            });
        });
    }

    render() {
        return (
            <TrackList audioTracks={this.state.audioTracks}/>
        );
    }

}

class TrackList extends React.Component {

    render() {
        const tracks = this.props.audioTracks.map(track => {
            return <AudioTrack key={track._links.self.href} track={track}/>
        });
        return (

            <div className={"tracks"}>
                <div className={"top-bar display"}>
                    <div>Title</div>
                    <div>Genres</div>
                    <div>Album</div>
                    <div>Duration</div>
                </div>
                <div className={"track-list"}>
                    {tracks}
                </div>
            </div>

        )
    }


}

class AudioTrack extends React.Component {

    render() {
        const title = this.props.track.title
        return (
            <div className={"track display"}>
                <h3>{title != null ? title : this.props.track.fileName}</h3>
                <div>{this.props.track.author}</div>
                <div>{this.props.track.album}</div>
                <div>
                    {
                        this.props.track.genres.map(genre => {
                            return <a href={"#"}>{genre + " " }</a>
                        })
                    }
                </div>
                <div>{this.props.track.durationSeconds}</div>

            </div>

        )
    }

    formatTimeSeconds(seconds) {

    }

}

createRoot(document.getElementById("react")).render(<App/>);