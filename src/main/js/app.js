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
            console.log(response);
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
                            return <a key={genre} href={"#"}>{genre + " " }</a>
                        })
                    }
                </div>
                <div>{this.formatTimeSeconds(this.props.track.durationSeconds)}</div>

            </div>

        )
    }

    formatTimeSeconds = (seconds) => {
       let date = new Date(seconds * 1000);
        console.log(date)
        console.log(date.toISOString())
       return date.toISOString().substring(11, 19);

    }

}
// 1970-01-01T00:01:58.000Z

createRoot(document.getElementById("react")).render(<App/>);