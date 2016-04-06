package com.example.leo.momentcontact;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by minsukmoon on 2016-03-07.
 */
public class GalSubActivity extends ActionBarActivity
{
    TextView title, nameOfLocation, description;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subgallery);
        title=(TextView)findViewById(R.id.title);
        nameOfLocation=(TextView)findViewById(R.id.nameOfLocation);
        description=(TextView)findViewById(R.id.description);

        description.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();
        int imgnum = intent.getIntExtra("imgnum", 0);

        ImageView bigimg = (ImageView) findViewById(R.id.bigimg);
        if(imgnum == 0) {

            bigimg.setImageResource(R.drawable.pic1);
            bigimg.setScaleType(ImageView.ScaleType.FIT_XY);
            title.setText("Stanley park");
            nameOfLocation.setText("Water fun");
            description.setText("   The Second Beach Pool is right on the beach, and has the advantage of being heated, even though it’s outdoors. Talk about having the best of both worlds! There is a waterslide and other interesting features for the kids and a lap lane for those looking to get in a little exercise on their vacation. The pool was built in 1995 to replace draw-and-fill ocean-side pools that would use the currents to take in salt water once a week. Second Beach Pool is a Vancouver Parks Board facility, and is open from Victoria Day at the end of May through Labour Day at the beginning of September.");


        }
        else if(imgnum == 1) {
            bigimg.setImageResource(R.drawable.pic2);
            bigimg.setScaleType(ImageView.ScaleType.FIT_XY);
            title.setText("Stanley park");
            nameOfLocation.setText("The Stanley Park Pavilion");
            description.setText("   The Stanley Park Pavilion is one of the most distinguished buildings in the entire park - and it's also one of the most historic. Built in 1911, it's an architectural draw located in the park's famous rose gardens. The main draw is the building’s restaurant, Stanley's Bar & Grill. The eatery features a casual menu using many local ingredients, and a beer and wine list that focuses on Vancouver and British Columbia producers.");
        }
        else if(imgnum == 2) {
            bigimg.setImageResource(R.drawable.pic3);
            bigimg.setScaleType(ImageView.ScaleType.FIT_XY);
            title.setText("Stanley park");
            nameOfLocation.setText("Seawall");
            description.setText("   Stanley Park gets huge kudos for being one of the great urban recreation areas in North America. But it  has some pretty darn good food, too. Ranging from the upscale divine to the quick and casual, it's easy  to find the right dining experience for a romantic dinner for two or a midday snack. ");
        }
        else if(imgnum == 3) {
            bigimg.setImageResource(R.drawable.pic4);
            bigimg.setScaleType(ImageView.ScaleType.FIT_XY);
            title.setText("Stanley park");
            nameOfLocation.setText("Vancouver Aquarium");
            description.setText("    The Vancouver Aquarium is widely respected for its research and marine stewardship, and visitors can learn more about the region's marine life through the numerous exhibits. Adults and kids alike will love the entertaining and educational \"encounters\" program, which allows visitors to go behind the scenes and get up close and personal with the animals and the people who train them. This includes feeding, helping train and learning about the habitats and lifestyles of dolphins, beluga whales, sea lions, sea otters, sea turtles and other sea creatures.");
        }
        else if(imgnum == 4) {
            bigimg.setImageResource(R.drawable.pic5);
            bigimg.setScaleType(ImageView.ScaleType.FIT_XY);
            title.setText("Stanley park");
            nameOfLocation.setText("The tea house");
            description.setText("   The Teahouse serves fine-dining fare in an exceptional setting.  Featuring local ingredients, the menu offers a fusion of West Coast flavours with a touch of French flair,  including salmon, one of B.C.’s signature dishes. The wine list is as impressive as the views. While  dinners shine, the weekend brunch is equally as big a hit. Did we mention the view?");
        }
        else if(imgnum == 5) {
            bigimg.setImageResource(R.drawable.pic6);
            title.setText("Gas Town");
            nameOfLocation.setText("Steam clock");
            description.setText("   A steam clock is a clock which is fully or partially powered by a steam engine. Only a few functioning steam clocks exist, most designed and built by Canadian horologist Raymond Saunders for display in urban public spaces. Steam clocks built by Saunders are located in Otaru, Japan; Indianapolis, USA; and the Canadian cities of Vancouver, Whistler and Port Coquitlam, all in British Columbia. Steam clocks by other makers are installed in St Helier, Jersey and at the Chelsea Farmers' Market in London, England.");
        }
        else if(imgnum == 6) {
            bigimg.setImageResource(R.drawable.pic7);
            title.setText("Gas Town");
            nameOfLocation.setText("Coastal Peoples");
            description.setText("   Coastal Peoples showcases museum-quality artwork representing some of the finest master carvers and newly emerging talent on the west coast. Focused on an unparalleled collection of contemporary artwork, identifying the Northwest Coast and Inuit communities of Northeastern Canada, newcomers and collectors alike can enjoy the gallery in person or online to gain access to a monumental collection. ");
        }
        else if(imgnum == 7) {
            bigimg.setImageResource(R.drawable.pic8);
            title.setText("Gas Town");
            nameOfLocation.setText("Vancouver Film School");
            description.setText("   From its inception in 1987, Vancouver Film School (VFS) has focused its business on entertainment related educational products. VFS has become the world’s pre-eminent centre for delivering quantifiably superior educational curriculum in highly accelerated time frames offering full time landmark ‘results orientated’ production programs in all facets of the entertainment industry.");
        }
        else if(imgnum == 8) {
            bigimg.setImageResource(R.drawable.pic9);
            title.setText("Gas Town");
            nameOfLocation.setText("Gassy Jack statue");
            description.setText("   Captain John “Gassy Jack” Deighton is a local legend and namesake for the area of downtown Vancouver known as Gastown. The Gassy Jack statue is one of the most photographed in Vancouver and is this week’s featured Vancouver Icon.Saloon proprietor Deighton headed toward the Burrard Inlet by canoe from New Westminster and saw a need that he could fulfill near the mill. In 1867 he told the workers in the harbour that they could have all the whiskey they could drink if they helped him build a saloon. Within 24 hours “The Globe” was up and running. While there is nothing left of The Globe (or much else of Vancouver from that time period due to The Great Fire) the statue of Gassy Jack stands at Maple Tree Square, where Alexander, Carrall, Water and Powell meet.");



        }
        else if(imgnum == 9) {
            bigimg.setImageResource(R.drawable.pic10);
            title.setText("Gas Town");
            nameOfLocation.setText("The diamond");
            description.setText("   Although it’s been open for a just over 2 years now, The Diamond is one Gastown, ahem, gem that is easy to miss. Some friends and I discovered it rather surreptitiously one evening when the street level Maple Street Square eatery we planned on trying was full. We stood in the square hemming and hawing about what to do next, looked to the heavens for an answer, and there it was: one floor up, art deco chandeliers calling to us from giant windows. But how to get into this magical, elevated place? A brief search brought us to a simple street-talker sign featuring a great big graphic of a cut diamond and behind it, a large set of stairs");

        }

    }
}
