package eu.ensg.jpo.explor_descartes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.location.Location;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.support.annotation.NonNull;

// Classes needed to initialize the map
import com.mapbox.geojson.BoundingBox;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.BubbleLayout;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponentOptions;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

// Classes needed to handle location permissions
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Classes needed to add the location engine
import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineCallback;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.android.core.location.LocationEngineRequest;
import com.mapbox.android.core.location.LocationEngineResult;
import java.lang.ref.WeakReference;

// Classes needed to add the location component
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.style.layers.Property;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.light.Position;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.mapboxsdk.style.sources.Source;

import eu.ensg.jpo.explor_descartes.donnesObjet.Batiment;
import eu.ensg.jpo.explor_descartes.donnesObjet.Ecole;
import eu.ensg.jpo.explor_descartes.donnesObjet.Evenement;

import static com.mapbox.mapboxsdk.style.expressions.Expression.eq;
import static com.mapbox.mapboxsdk.style.expressions.Expression.get;
import static com.mapbox.mapboxsdk.style.expressions.Expression.literal;
import static com.mapbox.mapboxsdk.style.layers.Property.ICON_ANCHOR_BOTTOM;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAnchor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconSize;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconTranslate;

public class NavigationActivity extends template implements OnMapReadyCallback, PermissionsListener, MapboxMap.OnMapClickListener{

    // Variables needed to initialize a map
    private MapboxMap mapboxMap;
    private MapView mapView;
    // Variables needed to handle location permissions
    private PermissionsManager permissionsManager;
    // Variables needed to add the location engine
    private LocationEngine locationEngine;
    private long DEFAULT_INTERVAL_IN_MILLISECONDS = 1000L;
    private long DEFAULT_MAX_WAIT_TIME = DEFAULT_INTERVAL_IN_MILLISECONDS * 5;
    // Variables needed to listen to location updates
    private MainActivityLocationCallback callback = new MainActivityLocationCallback(this);
    private ArrayList<Batiment> listeBatiment = new ArrayList<>();
    public HashMap<String, View> viewMap = new HashMap<>();
    public HashMap<String, Bitmap> imagesMap = new HashMap<>();
    private NavigationActivity activity = this;
    public FeatureCollection featureCollection;
    private GeoJsonSource source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Mapbox access token is configured here. This needs to be called either in your application
        // object or in the same activity which contains the mapview.
        Mapbox.getInstance(this, getString(R.string.access_token));

        // This contains the MapView in XML and needs to be called after the access token is configured.
        setContentView(R.layout.activity_navigation);

        super.onCreate(savedInstanceState);
        contentTemp();

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

    }

    @Override
    protected void llayout(){
        setLayout(R.layout.activity_navigation);
    }


    @Override
    public void onMapReady(@NonNull final MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;

        mapboxMap.setStyle(Style.MAPBOX_STREETS,
                new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        enableLocationComponent(style);
                        style.getLayer("poi-label").setProperties(PropertyFactory.visibility(Property.NONE));

                        // Affichage des batiments depuis la base de donnees
                        ArrayList<Batiment> listeBatiment = ListeObjets.listeBatiment;
                        for (Batiment batiment : listeBatiment){
                            batiment.afficherSurCarte(style);
                        }

                        // Affichage des évènements favoris (si l'utilisateur est connecté)
                        if (ListeObjets.visiteur != null) {


                            // /!\ DEBUT TEST FAVORIS /!\
                            Evenement evenement0 = new Evenement(0, "Les points forts du DUT TC en alternance", "2018-02-02 11:00:00", "2018-02-02 11:45:00", "CFA Descartes", "CFA Descartes");
                            Evenement evenement1 = new Evenement(1, "Présentation du cycle ingénieur en géomatique", "2018-02-02 10:30:00", "2018-02-02 11:00:00", "ENSG-Géomatique", "ENSG");
                            ListeObjets.listeFavoris.add(evenement0);
                            ListeObjets.listeFavoris.add(evenement1);
                            // /!\ FIN TEST FAVORIS /!\

                            ArrayList<Evenement> listeFavoris = ListeObjets.listeFavoris;

                            setUpSource(style);
                            setUpImage(style);
                            setUpMarkerLayer(style);
                            setUpInfoWindowLayer(style);

                        }

                        // Ajout des listener
                        mapboxMap.addOnMapClickListener(NavigationActivity.this);

                    }
                });


    }

    public void setUpSource(Style style){
        String jsonFeatureCollection = "{\"type\": \"FeatureCollection\", \"features\": [";

        for (Evenement favori : ListeObjets.listeFavoris){
            jsonFeatureCollection += favori.getJson() + ",";
        }

        jsonFeatureCollection = jsonFeatureCollection.substring(0, jsonFeatureCollection.length() - 1) + "]}";
        System.out.println(jsonFeatureCollection);

        featureCollection = FeatureCollection.fromJson(jsonFeatureCollection);

        source = new GeoJsonSource("GEOJSON_SOURCE_ID", featureCollection);
        style.addSource(source);

        new GenerateViewIconTask(activity).execute(featureCollection);

        System.out.println("SetUpSource");
    }

    private void setUpImage(Style style){
        style.addImage("star", BitmapFactory.decodeResource(this.getResources(), R.drawable.star));

        System.out.println("SetUpImage");
    }

    private void setUpMarkerLayer(@NonNull Style loadedStyle) {
        Float[] translationMarker = new Float[2];
        translationMarker[0] = new Float(0);
        translationMarker[1] = new Float(0);
        loadedStyle.addLayer(new SymbolLayer("MARKER_LAYER_ID", "GEOJSON_SOURCE_ID").withProperties(iconImage("star"),iconAllowOverlap(true), iconTranslate(translationMarker), iconSize(0.7f)));
        System.out.println("SetUpMarkerLayer");
    }

    private void setUpInfoWindowLayer(@NonNull Style loadedStyle) {
        loadedStyle.addLayer(new SymbolLayer("CALLOUT_LAYER_ID", "GEOJSON_SOURCE_ID")
                .withProperties(
                        /* show image with id title based on the value of the name feature property */
                        iconImage("{nom}"),

                        /* set anchor of icon to bottom-left */
                        iconAnchor(ICON_ANCHOR_BOTTOM),

                        /* all info window and marker image to appear at the same time*/
                        iconAllowOverlap(true),

                        /* offset the info window to be above the marker */
                        iconOffset(new Float[] {-2f, -25f})
                )
                /* add a filter to show only when selected feature property is true */
                .withFilter(eq((get("selected")), literal(true))));
        System.out.println("SetUpInfo");
    }

    private boolean handleClickIcon(PointF screenPoint) {
        List<Feature> features = mapboxMap.queryRenderedFeatures(screenPoint, "MARKER_LAYER_ID");
        if (!features.isEmpty()) {
            String name = features.get(0).getStringProperty("nom");
            List<Feature> featureList = featureCollection.features();
            for (int i = 0; i < featureList.size(); i++) {
                if (featureList.get(i).getStringProperty("nom").equals(name)) {
                    if (featureSelectStatus(i)) {
                        setFeatureSelectState(featureList.get(i), false);
                    } else {
                        setSelected(i);

                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean featureSelectStatus(int index) {
        if (featureCollection == null) {
            return false;
        }
        return featureCollection.features().get(index).getBooleanProperty("selected");
    }

    private void setSelected(int index) {
        Feature feature = featureCollection.features().get(index);
        setFeatureSelectState(feature, true);
        refreshSource();
    }

    private void setFeatureSelectState(Feature feature, boolean selectedState) {
        feature.properties().addProperty("selected", selectedState);
        refreshSource();
    }

    private void refreshSource() {
        if (source != null && featureCollection != null) {
            source.setGeoJson(featureCollection);
        }
    }

    public void setImageGenResults(HashMap<String, View> viewMap, HashMap<String, Bitmap> imageMap) {
        if (mapboxMap != null) {
            // calling addImages is faster as separate addImage calls for each bitmap.
            mapboxMap.getStyle().addImages(imageMap);
        }
        // need to store reference to views to be able to use them as hitboxes for click events.
        this.viewMap = viewMap;
    }

    private static class GenerateViewIconTask extends AsyncTask<FeatureCollection, Void, HashMap<String, Bitmap>> {

        private final HashMap<String, View> viewMap = new HashMap<>();
        private final WeakReference<NavigationActivity> activityRef;
        private final boolean refreshSource;

        GenerateViewIconTask(NavigationActivity activity, boolean refreshSource) {
            this.activityRef = new WeakReference<>(activity);
            this.refreshSource = refreshSource;
        }

        GenerateViewIconTask(NavigationActivity activity) {
            this(activity, false);
        }

        @SuppressWarnings("WrongThread")
        @Override
        protected HashMap<String, Bitmap> doInBackground(FeatureCollection... params) {


            NavigationActivity activity = activityRef.get();
            if (activity != null) {
                HashMap<String, Bitmap> imagesMap = new HashMap<>();
                LayoutInflater inflater = LayoutInflater.from(activity);

                FeatureCollection featureCollection = params[0];

                for (Feature feature : featureCollection.features()) {

                    BubbleLayout bubbleLayout = (BubbleLayout)
                            inflater.inflate(R.layout.layout_callout, null);

                    String name = feature.getStringProperty("nom");
                    TextView titleTextView = bubbleLayout.findViewById(R.id.title);
                    titleTextView.setText(name);

                    String horaires = feature.getStringProperty("debut").substring(11,16) + " - " + feature.getStringProperty("fin").substring(11,16);
                    TextView descriptionTextView = bubbleLayout.findViewById(R.id.horaires);
                    descriptionTextView.setText(horaires);

                    boolean favourite = feature.getBooleanProperty("favourite");
                    ImageView imageView = (ImageView) bubbleLayout.findViewById(R.id.logoView);
                    imageView.setImageResource(favourite ? R.drawable.star : R.drawable.star_stroked);



                    int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                    bubbleLayout.measure(measureSpec, measureSpec);

                    int measuredWidth = bubbleLayout.getMeasuredWidth();

                    bubbleLayout.setArrowPosition(measuredWidth / 2 - 5);

                    Bitmap bitmap = SymbolGenerator.generate(bubbleLayout);

                    imagesMap.put(name, bitmap);
                    viewMap.put(name, bubbleLayout);

                }
                System.out.println("Test 946 : " + viewMap.keySet());
                return imagesMap;
            } else {
                return null;
            }
        }

        @Override
        protected void onPostExecute(HashMap<String, Bitmap> bitmapHashMap) {
            super.onPostExecute(bitmapHashMap);
            NavigationActivity activity = activityRef.get();
            if (activity != null && bitmapHashMap != null) {
                activity.setImageGenResults(viewMap, bitmapHashMap);
                if (refreshSource) {
                    activity.refreshSource();
                }
            }
        }
    }

    /**
     * Utility class to generate Bitmaps for Symbol.
     */
    private static class SymbolGenerator {

        /**
         * Generate a Bitmap from an Android SDK View.
         *
         * @param view the View to be drawn to a Bitmap
         * @return the generated bitmap
         */
        static Bitmap generate(@NonNull View view) {
            int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(measureSpec, measureSpec);

            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();

            view.layout(0, 0, measuredWidth, measuredHeight);
            Bitmap bitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
            bitmap.eraseColor(Color.TRANSPARENT);
            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);
            System.out.println("Window generated");
            return bitmap;
        }
    }

    private void handleClickCallout(Feature feature, PointF screenPoint, PointF symbolScreenPoint) {
        System.out.println(feature.getStringProperty("nom"));
        System.out.println(viewMap.keySet());
        View view = viewMap.get(feature.getStringProperty("nom"));
        View textContainer = view.findViewById(R.id.text_container);

        // create hitbox for textView
        Rect hitRectText = new Rect();
        textContainer.getHitRect(hitRectText);

        // move hitbox to location of symbol
        hitRectText.offset((int) symbolScreenPoint.x, (int) symbolScreenPoint.y);

        // offset vertically to match anchor behaviour
        hitRectText.offset(0, -view.getMeasuredHeight());

        // hit test if clicked point is in textview hitbox
        if (hitRectText.contains((int) screenPoint.x, (int) screenPoint.y)) {
            // user clicked on text
            String callout = feature.getStringProperty("call-out");
            Toast.makeText(this, callout, Toast.LENGTH_LONG).show();
        } else {
            // user clicked on icon
            List<Feature> featureList = featureCollection.features();
            for (int i = 0; i < featureList.size(); i++) {
                if (featureList.get(i).getStringProperty("nom").equals(feature.getStringProperty("nom"))) {
                    toggleFavourite(i);
                }
            }
        }
    }

    private void toggleFavourite(int index) {
        Feature feature = featureCollection.features().get(index);
        String title = feature.getStringProperty("nom");
        boolean currentState = feature.getBooleanProperty("favourite");
        feature.properties().addProperty("favourite", !currentState);
        View view = viewMap.get(title);

        ImageView imageView = (ImageView) view.findViewById(R.id.logoView);
        imageView.setImageResource(currentState ? R.drawable.star : R.drawable.star_stroked);
        Bitmap bitmap = SymbolGenerator.generate(view);
        this.mapboxMap.getStyle().addImage(title, bitmap);
        refreshSource();
    }


    /**
     * Initialize the Maps SDK's LocationComponent
     */
    @SuppressWarnings( {"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
    // Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(this)) {

            // Get an instance of the component
            LocationComponent locationComponent = mapboxMap.getLocationComponent();

            LocationComponentOptions locationComponentOptions = LocationComponentOptions.builder(this).build();

            LocationComponentActivationOptions locationComponentActivationOptions = LocationComponentActivationOptions
                    .builder(this, loadedMapStyle)
                    .locationComponentOptions(locationComponentOptions)
                    .build();
            locationComponent.activateLocationComponent(this, loadedMapStyle);



            // Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);

            // Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);

            // Set the component's render mode
            locationComponent.setRenderMode(RenderMode.COMPASS);

            initLocationEngine();
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }

    /**
     * Set up the LocationEngine and the parameters for querying the device's location
     */
    @SuppressLint("MissingPermission")
    private void initLocationEngine() {
        locationEngine = LocationEngineProvider.getBestLocationEngine(this);

        LocationEngineRequest request = new LocationEngineRequest.Builder(DEFAULT_INTERVAL_IN_MILLISECONDS)
                .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
                .setMaxWaitTime(DEFAULT_MAX_WAIT_TIME).build();

        locationEngine.requestLocationUpdates(request, callback, getMainLooper());
        locationEngine.getLastLocation(callback);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            if (mapboxMap.getStyle() != null) {
                enableLocationComponent(mapboxMap.getStyle());
            }
        } else {
            Toast.makeText(this, R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void openEcoleActivity(Ecole ecole) {

        // Create intent
        Intent intent = new Intent(this, EcoleActivity.class);
        ListeObjets.ecoleSelectionnee = ecole;

        // Start activity
        startActivity(intent);
    }

    // Ajout de listener sur les batiments
    @Override
    public boolean onMapClick(@NonNull LatLng point) {
        PointF screenPoint = mapboxMap.getProjection().toScreenLocation(point);
        List<Feature> features = mapboxMap.queryRenderedFeatures(screenPoint, "CALLOUT_LAYER_ID");
        if (!features.isEmpty()) {
            // we received a click event on the callout layer
            Feature feature = features.get(0);
            PointF symbolScreenPoint = mapboxMap.getProjection().toScreenLocation(convertToLatLng(feature));
            handleClickCallout(feature, screenPoint, symbolScreenPoint);
        } else {
            // we didn't find a click event on callout layer, try clicking maki layer
            handleClickIcon(screenPoint);
        }
        /*
        PointF pointf = mapboxMap.getProjection().toScreenLocation(point);
        RectF rectF = new RectF(pointf.x - 2, pointf.y - 2, pointf.x + 2, pointf.y + 2);
        for (Evenement favori : ListeObjets.listeFavoris){
            if (mapboxMap.queryRenderedFeatures(rectF, "favori" + favori.getId()).size() > 0){
                Feature selectedFeature = mapboxMap.queryRenderedFeatures(rectF, "favori" + favori.getId()).get(0);
                String title = selectedFeature.getStringProperty("nom");
                Toast.makeText(this, "You selected " + title, Toast.LENGTH_SHORT).show();
                //PointF symbolScreenPoint = mapboxMap.getProjection().toScreenLocation(convertToLatLng(selectedFeature));
                //handleClickCallout(selectedFeature, pointf, symbolScreenPoint);
                return true;
            }
        }
        for (Batiment batiment : ListeObjets.listeBatiment) {
            if (mapboxMap.queryRenderedFeatures(rectF, "batiment" + batiment.getId()).size() > 0){
                int idEcole = batiment.getIdEcole();
                Toast.makeText(this, idEcole + "", Toast.LENGTH_LONG).show();
                NavigationActivity.this.openEcoleActivity(ListeObjets.getEcoleById(idEcole));
                return true;
            }
        }
        */
        return false;
    }

    private LatLng convertToLatLng(Feature feature) {
        Point symbolPoint = (Point) feature.geometry();
        return new LatLng(symbolPoint.latitude(), symbolPoint.longitude());
    }


    private static class MainActivityLocationCallback
            implements LocationEngineCallback<LocationEngineResult> {

        private final WeakReference<NavigationActivity> activityWeakReference;

        MainActivityLocationCallback(NavigationActivity activity) {
            this.activityWeakReference = new WeakReference<>(activity);
        }

        /**
         * The LocationEngineCallback interface's method which fires when the device's location has changed.
         *
         * @param result the LocationEngineResult object which has the last known location within it.
         */
        @Override
        public void onSuccess(LocationEngineResult result) {
            NavigationActivity activity = activityWeakReference.get();

            if (activity != null) {
                Location location = result.getLastLocation();

                if (location == null) {
                    return;
                }

                // Create a Toast which displays the new location's coordinates
                //Toast.makeText(activity, String.format(String.valueOf(result.getLastLocation().getLatitude()) + " " + String.valueOf(result.getLastLocation().getLongitude())),
                        //Toast.LENGTH_SHORT).show();

                // Pass the new location to the Maps SDK's LocationComponent
                if (activity.mapboxMap != null && result.getLastLocation() != null) {
                    activity.mapboxMap.getLocationComponent().forceLocationUpdate(result.getLastLocation());
                }
            }
        }

        /**
         * The LocationEngineCallback interface's method which fires when the device's location can not be captured
         *
         * @param exception the exception message
         */
        @Override
        public void onFailure(@NonNull Exception exception) {
            Log.d("LocationChangeActivity", exception.getLocalizedMessage());
            NavigationActivity activity = activityWeakReference.get();
            if (activity != null) {
                Toast.makeText(activity, exception.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }


    // Add the mapView's own lifecycle methods to the activity's lifecycle methods
    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Prevent leaks
        if (locationEngine != null) {
            locationEngine.removeLocationUpdates(callback);
        }
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    public ArrayList<Batiment> getListeBatiment() {
        return listeBatiment;
    }

    public void setListeBatiment(ArrayList<Batiment> listeBatiment) {
        this.listeBatiment = listeBatiment;
    }

    public MapboxMap getMapboxMap() {
        return mapboxMap;
    }
}
