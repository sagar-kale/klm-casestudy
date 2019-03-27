import { Coordinates } from './airports/coordinates';

export class Location {
    code: String;
    name: String;
    description: string;
    parent: Location;
    coordinates: Coordinates;
}