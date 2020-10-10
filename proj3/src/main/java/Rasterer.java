import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {

    public Rasterer() {
        // YOUR CODE HERE
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        //System.out.println(params);

        // get all parameters
        Double lrlon = params.get("lrlon");
        Double ullon = params.get("ullon");
        Double w = params.get("w");
        Double h = params.get("h");
        Double ullat = params.get("ullat");
        Double lrlat = params.get("lrlat");

        Double LonDPP = (ullon - lrlon) / w;
        String[][] render_grid;
        Double raster_ul_lon, raster_ul_lat, raster_lr_lon, raster_lr_lat;
        Integer depth;
        boolean query_success = true;
            Double first_depth = (MapServer.ROOT_ULLON - MapServer.ROOT_LRLON) / MapServer.TILE_SIZE;
            depth = (int) Math.ceil(Math.log(first_depth / LonDPP) / Math.log(2));
        if (depth > 7) {
            depth = 7;
        }

        //prepare
        double grid_width = (MapServer.ROOT_LRLON - MapServer.ROOT_ULLON) / Math.pow(2, depth);
        double grid_height = (MapServer.ROOT_ULLAT - MapServer.ROOT_LRLAT) / Math.pow(2, depth);
        int x_start = (int) Math.floor((ullon - MapServer.ROOT_ULLON) / grid_width);
        int y_start = (int) Math.floor((MapServer.ROOT_ULLAT - ullat) / grid_height);
        int x_end = (int) Math.pow(2, depth) - 1 - (int) Math.floor((MapServer.ROOT_LRLON - lrlon) / grid_width);
        int y_end = (int) Math.pow(2, depth) - 1 - (int) Math.floor((lrlat - MapServer.ROOT_LRLAT) / grid_height);

        // calculate all the items
        int x_num = x_end - x_start + 1;
        int y_num = y_end - y_start + 1;
        render_grid = new String[y_num][x_num];
        for (int i = 0; i < y_num; i += 1) {
            for (int j = 0; j < x_num; j += 1) {
                render_grid[i][j] = "d" + depth + "_x" + (x_start + j) + "_y" + (y_start + i) + ".png";
            }
        }
        raster_ul_lat = MapServer.ROOT_ULLAT - y_start * grid_height;
        raster_lr_lat = MapServer.ROOT_ULLAT - (y_end + 1) * grid_height;
        raster_ul_lon = MapServer.ROOT_ULLON + x_start * grid_width;
        raster_lr_lon = MapServer.ROOT_ULLON + (x_end + 1) * grid_width;

        // add all the item
        Map<String, Object> results = new HashMap<>();
        results.put("render_grid", render_grid);
        results.put("raster_ul_lon", raster_ul_lon);
        results.put("raster_ul_lat", raster_ul_lat);
        results.put("raster_lr_lon", raster_lr_lon);
        results.put("raster_lr_lat", raster_lr_lat);
        results.put("depth", depth);
        results.put("query_success", query_success);

        return results;
    }

}
