
package com.MWM.timberlink.ui.theme.screens.listings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Brand Colors
val ForestGreen = Color(0xFF1B5E20)
val MediumGreen = Color(0xFF2E7D32)
val LightGreen = Color(0xFF81C784)
val CreamWhite = Color(0xFFF9F6F0)
val WarmGray = Color(0xFF6D6D6D)
val CardWhite = Color(0xFFFFFFFF)

// Dummy Data Model
data class ListingItem(
    val id: String,
    val title: String,
    val species: String,
    val quantity: Int,
    val price: Double,
    val location: String,
    val sellerName: String,
    val emoji: String
)

// Dummy Listings
val dummyListings = listOf(
    ListingItem(
        id = "1",
        title = "Pine Plantation — Mature Trees",
        species = "Pine",
        quantity = 500,
        price = 450000.0,
        location = "Nakuru, Kenya",
        sellerName = "James Mwangi",
        emoji = "🌲"
    ),
    ListingItem(
        id = "2",
        title = "Eucalyptus Lot — Ready to Harvest",
        species = "Eucalyptus",
        quantity = 300,
        price = 280000.0,
        location = "Eldoret, Kenya",
        sellerName = "Grace Achieng",
        emoji = "🌳"
    ),
    ListingItem(
        id = "3",
        title = "Cypress Trees — Large Plantation",
        species = "Cypress",
        quantity = 800,
        price = 620000.0,
        location = "Nyeri, Kenya",
        sellerName = "Peter Kamau",
        emoji = "🌲"
    ),
    ListingItem(
        id = "4",
        title = "Mahogany — Premium Timber",
        species = "Mahogany",
        quantity = 150,
        price = 950000.0,
        location = "Kakamega, Kenya",
        sellerName = "Sarah Otieno",
        emoji = "🪵"
    ),
    ListingItem(
        id = "5",
        title = "Cedar Plantation — Mixed Age",
        species = "Cedar",
        quantity = 420,
        price = 380000.0,
        location = "Meru, Kenya",
        sellerName = "David Njoroge",
        emoji = "🌲"
    ),
    ListingItem(
        id = "6",
        title = "Wattle Trees — Fast Growing",
        species = "Wattle",
        quantity = 600,
        price = 210000.0,
        location = "Kericho, Kenya",
        sellerName = "Alice Chebet",
        emoji = "🌳"
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowseListingsScreen() {

    var searchQuery by remember { mutableStateOf("") }

    // Filter listings based on search
    val filteredListings = dummyListings.filter { listing ->
        listing.title.contains(searchQuery, ignoreCase = true) ||
                listing.species.contains(searchQuery, ignoreCase = true) ||
                listing.location.contains(searchQuery, ignoreCase = true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CreamWhite)
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            // Green Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(ForestGreen, MediumGreen)
                        )
                    )
                    .padding(24.dp)
                    .padding(top = 16.dp)
            ) {
                Column {

                    // Title Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "TimberLink",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                letterSpacing = 1.sp
                            )
                            Text(
                                text = "Find your timber today",
                                fontSize = 13.sp,
                                color = LightGreen
                            )
                        }
                        Text(text = "🌲", fontSize = 32.sp)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Search Bar
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = {
                            Text(
                                text = "Search by species, location...",
                                color = Color.White.copy(alpha = 0.6f),
                                fontSize = 14.sp
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Color.White
                            )
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Results Count
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${filteredListings.size} listings available",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = ForestGreen
                )
                Text(
                    text = "Kenya 🇰🇪",
                    fontSize = 13.sp,
                    color = WarmGray
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Listings
            if (filteredListings.isEmpty()) {
                // Empty State
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "🔍", fontSize = 48.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "No listings found",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = ForestGreen
                        )
                        Text(
                            text = "Try a different search term",
                            fontSize = 13.sp,
                            color = WarmGray
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        horizontal = 24.dp,
                        vertical = 8.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(filteredListings) { listing ->
                        ListingCard(listing = listing)
                    }
                    item { Spacer(modifier = Modifier.height(16.dp)) }
                }
            }
        }
    }
}

@Composable
fun ListingCard(listing: ListingItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            // Top colored banner with emoji
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                ForestGreen,
                                MediumGreen.copy(alpha = 0.7f)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(text = listing.emoji, fontSize = 48.sp)

                // Species Tag
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White.copy(alpha = 0.2f))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = listing.species,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            // Card Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                // Title
                Text(
                    text = listing.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1A1A)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Location Row
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location",
                        tint = ForestGreen,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = listing.location,
                        fontSize = 13.sp,
                        color = WarmGray
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Divider
                Divider(color = Color(0xFFF0F0F0))

                Spacer(modifier = Modifier.height(12.dp))

                // Bottom Row — Quantity, Price, Seller
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // Quantity
                    Column {
                        Text(
                            text = "Quantity",
                            fontSize = 11.sp,
                            color = WarmGray
                        )
                        Text(
                            text = "${listing.quantity} trees",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF1A1A1A)
                        )
                    }

                    // Price
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Price",
                            fontSize = 11.sp,
                            color = WarmGray
                        )
                        Text(
                            text = "KSh ${"%,.0f".format(listing.price)}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = ForestGreen
                        )
                    }

                    // Contact Button
                    Button(
                        onClick = { },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ForestGreen
                        ),
                        contentPadding = PaddingValues(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        )
                    ) {
                        Text(
                            text = "Enquire",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Seller Name
                Text(
                    text = "Listed by ${listing.sellerName}",
                    fontSize = 11.sp,
                    color = WarmGray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BrowseListingsScreenPreview() {
    BrowseListingsScreen()
}